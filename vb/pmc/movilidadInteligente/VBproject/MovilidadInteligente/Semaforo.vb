Imports VISSIM_COMSERVERLib

Public Class Semaforo
    Private sGroup As SignalGroup

    Public Sub New(sGroupP As SignalGroup)
        sGroup = sGroupP
    End Sub

    Public Sub cambiarAVerde()
        sGroup.State = 3 ' Pasar el signal head a rojo
    End Sub

    Public Sub cambiarARojo()
        sGroup.State = 1 ' Pasar el signal head a verde
        'Console.WriteLine("Semaforo.cambiarARojo() : Semáforo cambiado a rojo")
    End Sub

End Class
