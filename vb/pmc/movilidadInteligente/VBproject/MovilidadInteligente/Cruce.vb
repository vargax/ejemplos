Imports VISSIM_COMSERVERLib

Public Class Cruce
    ' ------------------------------------------------------------
    ' Constantes
    ' ------------------------------------------------------------
    ' La tolerancia permitida para la diferencia entre el promedio de velocidad del 
    ' carril de entrada y los carriles de salida.
    Private TOLERANCIA_PROM_ENTRADA_SALIDA As Double = 10
    ' El tiempo mínimo que permanecerá el semáforo habilitado mientras su prioridad
    ' sea diferente de 0
    Private TIEMPO_MIN As Integer = 6
    ' la velocidad por debajo de la cual se considerará que el cruce está bloqueado
    Private VEL_MIN_BLOQUEO As Double = 10
    ' ------------------------------------------------------------
    ' Atributos
    ' ------------------------------------------------------------
    Private id As String
    Private semaforo As Semaforo

    Private carrilEntrada As CarrilEntrada
    Private carrilesSalida As ArrayList

    Private habilitado As Boolean
    Private prioridad As Double
    Private tiempo As Integer ' El tiempo que lleva el cruce en su estado actual
    ' ------------------------------------------------------------
    ' Constructor y métodos de configuración
    ' ------------------------------------------------------------
    Public Sub New(idp As String, carrilEntradap As CarrilEntrada, carrilesSalidap As ArrayList, semaforop As Semaforo)
        id = idp
        carrilEntrada = carrilEntradap
        carrilesSalida = carrilesSalidap
        semaforo = semaforop

        habilitado = False
        tiempo = 0
        Console.WriteLine("INFO Cruce.new(): Creado el cruce " + id + " con " + carrilesSalida.Count.ToString + " carriles de salida")
    End Sub

    Public Sub registrarSensorEntrada(detector As Detector)
        carrilEntrada.registrarSensor(New Sensor(detector))
    End Sub
    ' ------------------------------------------------------------
    ' Métodos de operación
    ' ------------------------------------------------------------
    Public Sub habilitar()
        If Not habilitado Then
            tiempo = 0
            habilitado = True
            If Not semaforo Is Nothing Then
                'Console.WriteLine("Cruce.habilitar() : Habilitando el semaforo del cruce " + id)
                semaforo.cambiarAVerde()
            Else
                Console.WriteLine("ADVERTENCIA Cruce.habilitar() : El cruce " + id + " no tiene ningún semáforo")
            End If
        End If
    End Sub

    Public Sub deshabilitar()
        If habilitado Then
            tiempo = 0
            habilitado = False
            If Not semaforo Is Nothing Then
                'Console.WriteLine("Cruce.deshabilitar() : Deshabilitando el semaforo del cruce " + id)
                semaforo.cambiarARojo()
            Else
                Console.WriteLine("ADVERTENCIA Cruce.deshabilitar() : El cruce " + id + " no tiene ningún semáforo")
            End If
        End If
    End Sub

    Private Function actualizarPrioridad() As Double
        tiempo += 1
        ' Sin importar cual sea mi estado actual mi prioridad será 0 si:
        '1. No hay vehículos en el carril de entrada
        ' Primero verifico si todavía hay vehículos en el carril de entrada -> Caso trivial
        Dim velPromedioEntrada As Double = carrilEntrada.darVelocidadProm()
        If velPromedioEntrada = -1 Then 'La velocidad promedio es -1 si no hay vehículos => No se censan los sensores sin presencia
            Console.WriteLine("INFO Cruce.actualizarPrioridad(): No hay vehículos en espera para el cruce " + id)
            Return 0
        End If
        '2. Estoy bloqueado
        For Each carrilSalida As CarrilSalida In carrilesSalida
            Dim presenciaCandidata As Boolean = carrilSalida.hayPresencia
            Dim velocidadCandidata As Double = carrilSalida.darVelocidadProm
            'Console.WriteLine("INFO Cruce.actualizarPrioridad(): Carril Analizado: " + carrilSalida.darId.ToString + " Presencia: " +
            '                 presenciaCandidata.ToString + " Velocidad: " + velocidadCandidata.ToString)
            'Console.ReadLine() '!!!!!!!!!!!!!!!!!!!!!!! ====> DETIENE LA SIMULACIÓN!!
            ' Si hay presencia y la velocidad es cero es porque el carril de salida está bloqueado !
            If presenciaCandidata And velocidadCandidata < VEL_MIN_BLOQUEO Then
                Console.WriteLine("INFO Cruce.actualizarPrioridad(): Bloqueo detectado en el cruce " + id)
                'Console.WriteLine(velocidadCandidata.ToString)
                'Console.ReadLine() '!!!!!!!!!!!!!!!!!!!!!!! ====> DETIENE LA SIMULACIÓN!!
                Return 0
            End If
        Next

        ' Si estoy habilitado mi prioridad será la cola estimada
        If habilitado Then
            ' Si todavía hay vehículos entrando debo verificar presencia y velocidad en todos los carriles de salida
            ' y tomar el peor caso -> Empiezo en el mejor caso:
            Dim presencia As Boolean = False ' No hay presencia
            Dim velPromedioSalida As Double = velPromedioEntrada ' Velocidad salida igual a velocidad entrada

            For Each carrilSalida As CarrilSalida In carrilesSalida
                Dim presenciaCandidata As Boolean = carrilSalida.hayPresencia
                Dim velocidadCandidata As Double = carrilSalida.darVelocidadProm
                
                ' Si la velocidad candidata es -1 es porque no se censaron sensores, luego la ignoro
                If velocidadCandidata > -1 And presenciaCandidata And velocidadCandidata < velPromedioSalida Then
                    presencia = presenciaCandidata
                    velPromedioSalida = velocidadCandidata
                End If
            Next
            ' En este punto ya tengo el peor de los casos... voy a compararlo con los datos de la entrada
            If presencia And velPromedioEntrada - velPromedioSalida > TOLERANCIA_PROM_ENTRADA_SALIDA Then
                ' Estar bloqueado -> Hay presencia y delta velocidad > TOLERANCIA
                Console.WriteLine("INFO Cruce.actualizarPrioridad().habilitado: Posibilidad de bloqueo detectada para el cruce " + id)
                'Console.WriteLine("Presencia AND velPromEntrada - velPromSalida > TOLERANCIA : " + velPromedioEntrada.ToString + "-" +
                '                 velPromedioSalida.ToString + "=" + (velPromedioEntrada - velPromedioSalida).ToString)
                'Console.ReadLine() '!!!!!!!!!!!!!!!!!!!!!!! ====> DETIENE LA SIMULACIÓN!!
                Return 0
            End If
            Console.WriteLine("INFO Cruce.actualizarPrioridad().habilitado: Fijando prioridad en " + carrilEntrada.estimarCola.ToString + " para el cruce " + id)
            Return carrilEntrada.estimarCola
            ' Tendré que estimar el número de vehículos que podrían pasar -> Controlar entorno
            ' TODO !!!!!!!!!!!!!!!!!!!!!!!
            ' Si NO estoy habilitado
        Else ' Si mi estado es no Habilitado
            'Voy a estimar el número de vehículos que están en la cola y a ponderarlo por su tiempo de espera
            Dim cola As Double = carrilEntrada.estimarCola
            Dim espera As Double = carrilEntrada.darTiempoEsperaProm
            Console.WriteLine("INFO Cruce.actualizarPrioridad().noHabilitado: Cola * EsperaPromedio = " +
                              cola.ToString + "* e^" + espera.ToString + " = " + (cola * Math.Exp(espera)).ToString +
                              " para el cruce " + id)
            Return cola * Math.Exp(espera)
        End If
    End Function

    Public Function calcularPrioridad() As Double
        prioridad = actualizarPrioridad()
        ' Si estoy habilitado y llevo menos que el tiempo mínimo
        If prioridad > 0 And habilitado And tiempo < TIEMPO_MIN Then
            Console.WriteLine("INFO Cruce.calcularPrioridad(): Forzando el cruce " + id + " a permanecer ACTIVO | Tiempo: " + tiempo.ToString)
            Return -1 ' Forzo a la intersección a mantenerme habilitado
        End If
        ' En otro caso entrego mi prioridad estimada
        Return prioridad
    End Function


    ' ------------------------------------------------------------
    ' Getters
    ' ------------------------------------------------------------
    Public Function darId() As String
        Return id
    End Function

    Public Function darPrioridad() As Double
        Return prioridad
    End Function
End Class
