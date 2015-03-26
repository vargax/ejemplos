Imports VISSIM_COMSERVERLib

Public MustInherit Class Carril
    Protected id As String
    Protected sensores As ArrayList

    ' ------------------------------------------------------------
    ' Constructor y métodos de configuración
    ' ------------------------------------------------------------

    Public Sub New(idp As String)
        id = idp
        sensores = New ArrayList
    End Sub

    Public Sub registrarSensor(sensor As Sensor)
        sensores.Add(sensor)
    End Sub
  
    Public Function darId()
        Return id
    End Function

    ' ------------------------------------------------------------
    ' Métodos Operacion
    ' ------------------------------------------------------------
    Public Function hayPresencia() As Boolean
        Dim contador As Double = 0
        For Each sensor As Sensor In sensores
            If sensor.hayPresencia Then
                Console.WriteLine("Carril[" + id + "].hayPresencia(): El sensor " + sensor.darNombre + " detecta presencia")
                contador += 1
            End If
        Next
        Return contador / sensores.Count > 0.5
    End Function
    ' Entrega la velocidad promedio de los detectores que reportan presencia!!
    Public Overridable Function darVelocidadProm() As Double
        If sensores.Count = 0 Then
            Console.WriteLine("ADVERTENCIA Carril.darVelocidadProm(): El carril " + id + " no tiene detectores... velocidadPromedio = 0")
            Return 0
        End If

        Dim sumatoria As Double
        Dim sensoresCensados As Double = 0
        For Each sensor As Sensor In sensores
            If sensor.hayPresencia Then
                sumatoria += sensor.darVelocidad
                sensoresCensados += 1
            End If
        Next

        If sensoresCensados > 0 Then
            Return sumatoria / sensoresCensados
        Else
            'Console.WriteLine("ADVERTENCIA Carril.darVelocidadProm(): No se censaron sensores para el carril " + id + "... velocidadPromedio = -1 || " + sumatoria.ToString)
            'Console.ReadLine() '!!!!!!!!!!!!!!!!!!!!!!! ====> DETIENE LA SIMULACIÓN!!
            Return -1
        End If
    End Function

    Public Function darTiempoEsperaProm() As Double
        If sensores.Count = 0 Then
            Console.WriteLine("ADVERTENCIA Carril.darTiempoEsperaProm(): El carril " + id + " no tiene detectores... esperaPromedio = 0")
            Return 0
        End If

        Dim sumatoria As Double
        Dim sensoresConPresencia As Integer
        For Each Sensor As Sensor In sensores
            If Sensor.darTiempoEspera > 0 Then
                sumatoria += Sensor.darTiempoEspera
                sensoresConPresencia += 1
            End If
        Next

        If sensoresConPresencia > 0 Then
            Return sumatoria / sensoresConPresencia
        Else
            'Console.WriteLine("INFO Carril.darTiempoEsperaProm(): No se han detectado vehículos en el carril " + id + " ... esperaPromedio = 0")
            Return 0
        End If
    End Function

    Public MustOverride Sub debug()

End Class
