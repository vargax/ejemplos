Imports System.Windows.Forms

Public Class ModuloEntrada


    Dim vissim As VISSIM_COMSERVERLib.Vissim
    Dim simulacion As VISSIM_COMSERVERLib.Simulation
    Dim evaluacion As VISSIM_COMSERVERLib.Evaluation

    Dim intersecciones As Hashtable
    Dim rutaSimulacion As String

    Dim pasoApaso As Boolean




    Sub Simular(cajas As ArrayList)
        Console.WriteLine("MAIN: Iniciando simulación...")
        For i = 1 To ((simulacion.Period * simulacion.Resolution) / 15) - 1



            cambiarDatos(cajas)


            For j = 1 To 15
                simulacion.RunSingleStep()
            Next

            For Each de As DictionaryEntry In intersecciones
                Dim inter As Interseccion = de.Value()
                Console.WriteLine(".................................................")
                Console.WriteLine("... Intersección: " + inter.darId() + " | " + simulacion.AttValue("ELAPSEDTIME").ToString)
                Console.WriteLine(".................................................")
                inter.optimizar()
            Next

            vissim.DoEvents()
            If pasoApaso Then
                Console.WriteLine("----------------------------------")
                Console.WriteLine("-- ESTADO DE LA SIMULACIÓN:")
                Console.WriteLine("----------------------------------")
                Console.WriteLine("Iteración: " + i.ToString)
                For Each de As DictionaryEntry In intersecciones
                    Dim interseccion As Interseccion = de.Value()
                    Console.WriteLine("----------------------------------")
                    interseccion.imprimirId()
                    interseccion.imprimirCrucesActivos()
                    interseccion.imprimirCrucesRealizables()
                    interseccion.imprimirCrucesConPrioridad()
                Next

                Console.Write("MAIN: ¿Continuar? (s/n) ")
                If [String].Equals(Console.ReadLine(), "n") Then
                    Exit For
                End If
            End If
        Next
    End Sub

    Sub Inicializar(inter As InterfaceVehicles)

        Console.WriteLine("ADVERTENCIA : El símbolo decimal debe definirse como '.' en la configuración regional! => Afecta el funcionamiento de los Parsers ")
        'rutaSimulacion = "C:\Users\Pmc\Desktop\MOVILIDAD INTELIGENTE\simulaciones\caliHayuelos\cali hayuelosOptimizado.inp"
        rutaSimulacion = "C:\Users\Pmc\Desktop\MOVILIDAD INTELIGENTE\simulaciones\adhoc2\adhoc2.inp"
        Console.WriteLine("MAIN: Creando el objeto Vissim...")
        vissim = CreateObject("VISSIM.Vissim")
        Console.WriteLine("MAIN: Cargando red...")
        vissim.LoadNet(rutaSimulacion)
        Console.WriteLine("MAIN: Configurando simulación...")
        simulacion = vissim.Simulation
        simulacion.Resolution = 5
        simulacion.Period = 1000
        simulacion.Speed = 7
        Console.WriteLine("!! MAIN: ¿Desea detener la simulación tras cada iteración? (s/n)")
        pasoApaso = [String].Equals(Console.ReadLine(), "s")
        Console.WriteLine("MAIN: Instanciando Intersecciones...")
        '------------------------------------------------------------------------------------------------------
        'Links
        '------------------------------------------------------------------------------------------------------
        intersecciones = New Hashtable
        '------------------------------------------------------------------------------------------------------
        'inicializando datos de vehiculos en la interface
        '------------------------------------------------------------------------------------------------------
        Dim cajas As ArrayList = inter.definirCantidadCarros(vissim.Net.VehicleInputs.Count)
        Dim labels As ArrayList = inter.definirnombreCarros(vissim.Net.VehicleInputs.Count)
        Dim cont As Integer = 0
        For Each vehin As VISSIM_COMSERVERLib.VehicleInput In vissim.Net.VehicleInputs
            Dim text As TextBox = cajas(cont)
            If text.InvokeRequired Then
                Dim d As New Threading.ContextCallback(AddressOf cambiarDatos)
                text.Invoke(d, New Object() {cajas})
            Else
                text.Text = vehin.AttValue("VOLUME")
            End If

            Dim labelt As Label = labels(cont)
            If labelt.InvokeRequired Then
                Dim d As New Threading.ContextCallback(AddressOf cambiarDatoslabel)
                labelt.Invoke(d, New Object() {labels})
            Else
                labelt.Text = vehin.AttValue("NAME")
            End If




            cont = cont + 1
        Next vehin

        '------------------------------------------------------------------------------------------------------
        'Signal Controllers
        '------------------------------------------------------------------------------------------------------
        For Each sc In vissim.Net.SignalControllers
            'obtenemos el nombre del SignalControl
            Dim idSignalController As String = sc.AttValue("NAME")
            Dim interseccion As Interseccion = New Interseccion(idSignalController)
            intersecciones.Add(idSignalController, interseccion)
            interseccion.registrarSignalController(sc)
            interseccion.configurar()
        Next

    End Sub

    Private Sub cambiarDatos(cajas As ArrayList)
        Dim cont As Integer = 0
        For Each vehin As VISSIM_COMSERVERLib.VehicleInput In vissim.Net.VehicleInputs
            Dim text As TextBox = cajas(cont)
            If text.InvokeRequired Then
                Dim d As New Threading.ContextCallback(AddressOf cambiarDatos)
                text.Invoke(d, New Object() {cajas})
            Else
                vehin.AttValue("VOLUME") = text.Text
            End If
            cont = cont + 1
        Next vehin
    End Sub

    Private Sub cambiarDatoslabel(labels As ArrayList)
        Dim cont As Integer = 0
        For Each vehin As VISSIM_COMSERVERLib.VehicleInput In vissim.Net.VehicleInputs
            Dim labelt As Label = labels(cont)
            If labelt.InvokeRequired Then
                Dim d As New Threading.ContextCallback(AddressOf cambiarDatoslabel)
                labelt.Invoke(d, New Object() {labels})
            Else
                labelt.Text = vehin.AttValue("NAME")
            End If
            cont = cont + 1
        Next vehin
    End Sub

End Class
