Imports VISSIM_COMSERVERLib

Public Class CarrilEntrada
    Inherits Carril

    ' ------------------------------------------------------------
    ' Constructor
    ' ------------------------------------------------------------
    Public Sub New(idp As String)
        MyBase.New(idp)
    End Sub

    ' ------------------------------------------------------------
    ' Métodos Operación
    ' ------------------------------------------------------------
    Public Function estimarCola() As Double
        If sensores.Count = 0 Then
            Console.WriteLine("ADVERTENCIA CarrilEntrada.estimarCola(): El carril " + id + " no tiene sensores... colaEstimada = 0")
            Return 0
        End If
        ' En principio solo voy a calcular la proporción de sensores que
        ' notifican presencia en el total de sensores del carril
        Dim sensoresConPresencia As Double = 0
        For Each sensor As Sensor In sensores
            If sensor.hayPresencia Then
                sensoresConPresencia += 1
            End If
        Next
        ' Una razón cercana a uno indica que el área monitoreada se encuentra llena
        Return sensoresConPresencia / sensores.Count
    End Function

    Public Overrides Sub debug()
        Console.WriteLine("++ Carril de Entrada " + id + " contiene " + sensores.Count.ToString + " sensores")
        For Each sensor As Sensor In sensores
            Console.WriteLine(sensor.darNombre())
        Next
    End Sub
End Class
