Imports VISSIM_COMSERVERLib

Public Class CarrilSalida
    Inherits Carril
    ' ------------------------------------------------------------
    ' Constantes
    ' ------------------------------------------------------------
    Public Sub New(idp As String)
        MyBase.New(idp)
    End Sub

    ' ------------------------------------------------------------
    ' Métodos Operación
    ' ------------------------------------------------------------

    Public Overrides Sub debug()
        Console.WriteLine("++ Carril de Salida " + id + " contiene " + sensores.Count.ToString + " sensores")
        For Each sensor As Sensor In sensores
            Console.WriteLine(sensor.darNombre())
        Next
    End Sub
End Class
