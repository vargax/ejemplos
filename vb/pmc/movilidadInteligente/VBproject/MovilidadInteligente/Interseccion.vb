Imports VISSIM_COMSERVERLib

Public Class Interseccion
    ' ------------------------------------------------------------
    ' Constantes
    ' ------------------------------------------------------------
    ' Número de iteraciones tras las cuales la intersección forzará la deshabilitación
    ' de todos sus cruces => Alternativa temporal para superar los altos tiempos de espera
    Private TIEMPO_RESET As Integer = 20
    ' ------------------------------------------------------------
    ' Atributos
    ' ------------------------------------------------------------
    Private id As String

    Private tiempo As Integer

    Private entradas As Hashtable   'HashTable Carril de entrada 
    Private salidas As Hashtable    'HashTable Carril de salida 
    Private cruces As Hashtable     'HashTable cruces
    Private signalController As SignalController

    Private crucesActivos As ArrayList      'La lista de los cruces que deben permanecer activos
    Private crucesConPrioridad As ArrayList 'La lista de los cruces con prioridades > 0
    Private crucesRealizables As ArrayList  'La lista de los crucesConPrioridad que se pueden realizar sin interferir con los crucesActivos
    ' ------------------------------------------------------------
    ' Constructor
    ' ------------------------------------------------------------
    Public Sub New(idp As String)
        entradas = New Hashtable
        salidas = New Hashtable
        cruces = New Hashtable

        id = idp

        tiempo = 0
        'Console.WriteLine("Todos FALSE:")
        'Console.WriteLine(seCortan("-1,0;0,1", "0,-1;1,0"))
        'Console.WriteLine(seCortan("0,-1;1,0", "-1,0;0,1"))
        'Console.WriteLine(seCortan("-1,-1;0,0", "0,1;1,0"))

        'Console.WriteLine("Todos TRUE:")
        'Console.WriteLine(seCortan("-1,0;1,0", "1,0;-1,0"))
        'Console.WriteLine(seCortan("1,0;-1,0", "0,-1;1,0"))
        'Console.WriteLine(seCortan("-1,0;1,0", "0,-1;0,1"))
        'Console.WriteLine(seCortan("-1,0;2,0", "0,-1;1,1"))

        'generarSegmentos("-1,0;0,1:1,0:0,-1")
        'generarSegmentos("1,0;0,1:-1,0:0,-1")

    End Sub

    ' ------------------------------------------------------------
    ' Métodos Configuración
    ' ------------------------------------------------------------
    Private Function registrarEntrada(idOrigenDestinos As String) As CarrilEntrada
        Dim carrilEntrada As CarrilEntrada = New CarrilEntrada(idOrigenDestinos)
        entradas.Add(idOrigenDestinos, carrilEntrada)
        'Console.WriteLine("INFO Interseccion(" + id + ").registrarEntrada(): Creado y registrado el carril de entrada " + idOrigen)
        Return carrilEntrada
    End Function

    Private Function registrarSalida(idDestino As String) As CarrilSalida
        Dim carrilSalida As CarrilSalida = New CarrilSalida(idDestino)
        salidas.Add(idDestino, carrilSalida)
        'Console.WriteLine("INFO Interseccion(" + id + ").registrarSalida(): Creado y registrado el carril de salida " + idDestino)
        Return carrilSalida
    End Function

    Public Sub registrarSignalController(sc As SignalController)
        If signalController Is Nothing Then
            signalController = sc
            Console.WriteLine("Interseccion.registrarSemaforo(): Registrado el controlador de semáforos " + sc.Name + " en la intersección " + id)
        Else
            Console.WriteLine("!! Interseccion.registrarSemaforo(): ya hay registrado un signal controller en la interseccion " + id)
        End If


    End Sub

    Public Sub configurar()

        'Asignando semáforos
        For Each signal As SignalGroup In signalController.SignalGroups

            'obtenemos el nombre del semáforo
            Dim nombreSignal As String = signal.AttValue("NAME")
            If cruces(nombreSignal) Is Nothing Then

                'recuperamos su id [idCarrilEntrada;idCarrilSalida1:idCarrilSalida2:...:idCarrilSalidaN]
                Dim identificadores = nombreSignal.Split(";")
                Dim idCarrilEntrada = nombreSignal
                Dim cEntrada As CarrilEntrada = entradas(idCarrilEntrada)
                If cEntrada Is Nothing Then
                    cEntrada = registrarEntrada(idCarrilEntrada)
                End If

                Dim idsSalidas = identificadores(1).Split(":")
                Dim carrilesSalida = New ArrayList
                For Each idSalida In idsSalidas
                    Dim cSalida As CarrilSalida = salidas(idSalida)
                    If cSalida Is Nothing Then
                        cSalida = registrarSalida(idSalida)
                    End If
                    carrilesSalida.Add(cSalida)
                Next
                Dim cruce As Cruce = New Cruce(nombreSignal, cEntrada, carrilesSalida, New Semaforo(signal))
                cruces.Add(nombreSignal, cruce)
            Else
                Console.WriteLine("!! Interseccion.configurar(): El cruce " + nombreSignal + " no debería existir todavía!")
            End If
        Next

        'Asignando sensores
        Dim detectoresSalida As ArrayList = New ArrayList
        For Each detector As Detector In signalController.Detectors
            Dim nombreDetector As String = detector.AttValue("NAME") 'Recupero el ID del link donde está ubicado el detector
            Dim datos = nombreDetector.Split("#")
            If Not datos.Length = 3 Then
                Console.WriteLine("!! Interseccion.configurar().asignando sensores: Error en el formato del detector:" + detector.AttValue("ID"))
            Else
                'el nombre del cruce es el mismo que tienen los signal groups si son de entrada, 
                'si son de salida sera el nombre de la interseccion hacia donde se dirigen, seran comunitarios
                Dim nombreCarril = datos(0)
                'el tipo de carril es E si es de entrada o S si es de salida
                Dim tipoCarril = datos(1)
                'la posicion es la ubicacion del sensor, si es de entrada la posicion estara mas cerca al semaforo y 
                'si es de salida la posicon 1 es la mas cercana al inicio del carril
                Dim posicion = datos(2)
                '  Console.WriteLine("nombre cruce: " + nombreCruce + "tipoCarril: " + tipoCarril + " posicion: " + posicion)

                If tipoCarril = "E" Then
                    Dim carrilEntrada As CarrilEntrada = entradas(nombreCarril)
                    carrilEntrada.registrarSensor(New Sensor(detector))
                ElseIf tipoCarril = "S" Then
                    Dim carrilSalida As CarrilSalida = salidas(nombreCarril)
                    carrilSalida.registrarSensor(New Sensor(detector))
                Else
                    Console.WriteLine("!! Interseccion.configurar().asigando sensores: error en el formato del tipo de carril:" + tipoCarril)
                End If
            End If
        Next

        For Each de As DictionaryEntry In entradas
            de.Value.debug()
        Next
        For Each de As DictionaryEntry In salidas
            de.Value.debug()
        Next
    End Sub

    ' ------------------------------------------------------------
    ' Métodos Operación
    ' ------------------------------------------------------------
    Public Sub optimizar()
        ' Verifico condición RESET
        reset()
        ' Obtengo las prioridades de cada cruce
        obtenerPrioridades()
        ' Calculo los cruces realizables
        calcularCrucesRealizables()
        ' Habilitar el cruce realizable con la prioridad más alta
        Dim crucesDeshabilitar As ArrayList = New ArrayList
        If crucesRealizables.Count > 0 Then
            Dim cruceMayorPrioridad As Cruce = crucesRealizables(0)
            For i = 1 To crucesRealizables.Count - 1
                If cruceMayorPrioridad.darPrioridad() < crucesRealizables(i).darPrioridad Then
                    crucesDeshabilitar.Add(cruceMayorPrioridad)
                    cruceMayorPrioridad = crucesRealizables(i)
                Else
                    crucesDeshabilitar.Add(crucesRealizables(i))
                End If
            Next
            Console.WriteLine("!!++ Habilitando el cruce " + cruceMayorPrioridad.darId)
            cruceMayorPrioridad.habilitar()
        End If
        ' Forzar la deshabilitación de los demás cruces realizables cuya prioridad es baja
        For Each cruce As Cruce In crucesDeshabilitar
            cruce.deshabilitar()
        Next
    End Sub

    'Obtiene la prioridad estiamada de cada cruce
    Private Sub obtenerPrioridades()
        crucesActivos = New ArrayList
        crucesConPrioridad = New ArrayList

        ' Recorrer todos los cruces
        For Each de As DictionaryEntry In cruces
            Dim cruce As Cruce = de.Value()
            Dim prioridad As Double = cruce.calcularPrioridad()
            'Console.WriteLine("INFO Interseccion(" + id + ").optimizar(): La prioridad del cruce " + cruce.darId + " es " + prioridad.ToString)
            If prioridad = -1 Then
                ' Estos son los cruces que deben mantenerse activos
                crucesActivos.Add(cruce)
            ElseIf prioridad = 0 Then
                ' Deshabilitar aquellos cuya prioridad sea cero
                cruce.deshabilitar()
            ElseIf prioridad > 0 Then
                ' Almacenar los cruces cuya prioridad sea mayor a cero
                crucesConPrioridad.Add(cruce)
            Else
                Console.WriteLine("!! ADVERTENCIA Interseccion(" + id + ").optimizar(): El cruce " + cruce.darId +
                                  " reporta una prioridad no contemplada de " + prioridad)
            End If
        Next
    End Sub

    'Calcula los cruces realizables (evitando coliciones) entre el conjuto de cruces con prioridad 
    'sujeto a evitar coliciones con los cruces que deben permanecer activos
    Private Sub calcularCrucesRealizables()
        crucesRealizables = New ArrayList
        '** CADA CRUCE PUEDE TENER VARIOS SEGMENTOS
        ' Primero genero los segmentos de los cruces activos
        Dim segmentosActivos As ArrayList = New ArrayList
        For Each cruceActivo As Cruce In crucesActivos
            segmentosActivos.AddRange(generarSegmentos(cruceActivo.darId))
        Next

        ' Ahora voy a comprar los segmentos de cada cruce candidato con los segmentos activos
        For Each cruceCandidato As Cruce In crucesConPrioridad
            Dim segmentosCandidato As ArrayList = generarSegmentos(cruceCandidato.darId)
            Dim continuar As Boolean = True

            Dim i As Integer = 0
            While continuar And i < segmentosActivos.Count
                Dim segmentoActivo As String = segmentosActivos(i) ' Cada uno de los segmentos activos
                'Console.WriteLine("++++++ SEGMENTO ACTIVO: " + segmentoActivo)
                Dim j As Integer = 0
                While continuar And j < segmentosCandidato.Count
                    Dim segmentoCandidato As String = segmentosCandidato(j) ' Cada uno de los segmentos candidatos
                    'Console.WriteLine("++++++ SEGMENTO CANDIDATO: " + segmentoCandidato)
                    ' Verificar corte entre CADA uno de los segmentos de CADA cruce candidato contra todos los segmentos de los cruces activos
                    continuar = Not seCortan(segmentoActivo, segmentoCandidato)
                    'If continuar Then
                    '    Console.WriteLine("++++ Los segmentos " + segmentoActivo + " | " + segmentoCandidato + " NO se cortan")
                    'Else
                    '    Console.WriteLine("++++ Los segmentos " + segmentoActivo + " | " + segmentoCandidato + " SE CORTAN!! ")
                    'End If
                    j += 1
                End While
                i += 1
            End While

            ' Si llegó aquí con continuar = true es porque ninguno de los segmentos del cruce candidato se cruzan con los segmentos de los cruces activos
            If continuar Then
                'Console.WriteLine("++ El cruce " + cruceCandidato.darId + " es realizable!!")
                crucesRealizables.Add(cruceCandidato) ' -> es un cruce realizable
            Else
                'Console.WriteLine("++ El cruce " + cruceCandidato.darId + " NO es realizable")
            End If
        Next
    End Sub

    Private Sub reset()
        If tiempo > TIEMPO_RESET Then
            tiempo = 0
            For Each de As DictionaryEntry In cruces
                de.Value.deshabilitar()
            Next
            Console.WriteLine("INFO Intersección.reset(): TIMEOUT! => Forzando RESET de la intersección")
        Else
            tiempo += 1
        End If
    End Sub

    ' ------------------------------------------------------------
    ' Métodos Auxiliares
    ' ------------------------------------------------------------
    ' Genera las cadenas que representan los segmentos del cruce pasado como parámetro
    Private Function generarSegmentos(cruce As String) As ArrayList
        Dim coorOrigen As String = cruce.Split(";")(0)
        Dim coorDestinos As Array = (cruce.Split(";")(1)).Split(":")

        ' Genero los segmentos
        Dim segmentos As ArrayList = New ArrayList
        For Each coorDestino As String In coorDestinos
            segmentos.Add(coorOrigen + ";" + coorDestino)
        Next

        Return segmentos
    End Function

    ' Evalua posición del punto {pX,pY} respecto al segmento {sX1,sY1;sX2,sY2}
    ' Si > 0 -> DERECHA | Si < 0 -> IZQUIERDA | Si = 0 -> SOBRE EL SEGMENTO
    Private Function izq_der(punto As String, segmento As String) As Double
        ' Parser coordenadas
        Dim coordenadasPunto = punto.Split(",")
        Dim pX As Double = Double.Parse(coordenadasPunto(0))
        Dim pY As Double = Double.Parse(coordenadasPunto(1))

        Dim puntosSegmento = segmento.Split(";")
        Dim coorPto1Segmento = puntosSegmento(0).Split(",")
        Dim sX1 As Double = Double.Parse(coorPto1Segmento(0))
        Dim sY1 As Double = Double.Parse(coorPto1Segmento(1))
        Dim coorPto2Segmento = puntosSegmento(1).Split(",")
        Dim sX2 As Double = Double.Parse(coorPto2Segmento(0))
        Dim sY2 As Double = Double.Parse(coorPto2Segmento(1))

        ' Generando vectores
        ' vec1 : Del primer punto del segmento al segundo punto del segmento
        Dim vec1X As Double = pX - sX1
        Dim vec1Y As Double = pY - sY1
        ' vec2 : Del primer punto del segmento al punto pasado como parámetro
        Dim vec2X As Double = sX2 - sX1
        Dim vec2Y As Double = sY2 - sY1

        Return (vec1X * vec2Y) - (vec2X * vec1Y) ' => Producto cruz / Regla de la mano derecha
    End Function

    ' Retorna TRUE si los dos segmentos pasados como parámetros se cortan
    ' Se considera que se cortan si tienen al menos un punto en común (incluso los extremos)
    ' - EXCEPTO si tiene los dos puntos en común!!
    ' - EXCEPTO si el punto de origen es el mismo!!
    ' - EXCEPTO caso en que comparten un punto
    Private Function seCortan(segmento1 As String, segmento2 As String) As Boolean
        ' El caso en el cual los extremos son los mismos!!
        Dim rev As String = segmento1.Split(";")(1) + ";" + segmento1.Split(";")(0)
        If rev.Equals(segmento2) Then
            Return False
        End If
        ' El caso en el cual el punto de origen es el mismo!!
        If (segmento1.Split(";")(0)).Equals(segmento2.Split(";")(0)) Then
            Return False
        End If

        ' Si comparten un punto toca determinar si el punto que no comparten está a la derecha
        ' o a la izquierda
        Dim coor1seg1 As String = segmento1.Split(";")(0)
        Dim coor2seg1 As String = segmento1.Split(";")(1)
        Dim coor1seg2 As String = segmento2.Split(";")(0)
        Dim coor2seg2 As String = segmento2.Split(";")(1)
        If coor1seg1.Equals(coor2seg2) And izq_der(coor2seg1, segmento2) < 0 Or
           coor2seg1.Equals(coor1seg2) And izq_der(coor2seg2, segmento1) < 0 Then
            Return False
        End If

        ' Los demás casos
        Dim pto1 As Double = izq_der(segmento1.Split(";")(0), segmento2)
        Dim pto2 As Double = izq_der(segmento1.Split(";")(1), segmento2)
        If pto1 * pto2 <= 0 Then
            pto1 = izq_der(segmento2.Split(";")(0), segmento1)
            pto2 = izq_der(segmento2.Split(";")(1), segmento1)
            If pto1 * pto2 <= 0 Then
                Return True
            End If
        End If
        Return False
    End Function

    ' ------------------------------------------------------------
    ' Métodos Debug
    ' ------------------------------------------------------------
    Public Function darId() As String
        Return id
    End Function

    Public Sub imprimirId()
        Console.WriteLine("Intersección " + id)
    End Sub
    Public Sub imprimirCrucesActivos()
        Console.WriteLine("   --------------------")
        Console.WriteLine("  Cruces Activos:")
        Console.WriteLine()
        For Each cruce As Cruce In crucesActivos
            Console.WriteLine("  " + cruce.darId)
        Next
    End Sub
    Public Sub imprimirCrucesRealizables()
        Console.WriteLine("   --------------------")
        Console.WriteLine("  Cruces Realizables:")
        Console.WriteLine()
        For Each cruce As Cruce In crucesRealizables
            Console.WriteLine("  " + cruce.darId + " : " + cruce.darPrioridad.ToString)
        Next
    End Sub
    Public Sub imprimirCrucesConPrioridad()
        Console.WriteLine("   --------------------")
        Console.WriteLine("  Cruces con Prioridad:")
        Console.WriteLine()
        For Each cruce As Cruce In crucesConPrioridad
            Console.WriteLine("  " + cruce.darId + " : " + cruce.darPrioridad.ToString)
        Next
    End Sub
End Class
