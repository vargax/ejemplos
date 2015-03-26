Imports VISSIM_COMSERVERLib

Public Class Sensor

    Private detector As Detector
    Private nombre As String
    Private nombreCruce As String
    Private tipoCarril As String
    Private posicion As String

    Public Sub New(detectorp As Detector)
        detector = detectorp
        nombre = detector.AttValue("NAME")
        Dim datos = nombre.Split("#")

        ' el nombre del cruce es el mismo que tienen los signal groups si son de entrada, 
        'si son de salida sera el nombre de la interseccion hacia donde se dirigen, seran comunitarios
        nombreCruce = datos(0)
            'el tipo de carril es E si es de entrada o S si es de salida
        tipoCarril = datos(1)
            'la posicion es la ubicacion del sensor, si es de entrada la posicion estara mas cerca al semaforo y 
            'si es de salida la posicon 1 es la mas cercana al inicio del carril
        posicion = datos(2)
            '  Console.WriteLine("Sensor.New(): Creado el sensor " + detector.ID.ToString + " en la posición " + detector.AttValue("POSITION").ToString)
    End Sub

    Public Function darNombre() As String
        Return nombre
    End Function

    Public Function darId() As String
        Return nombreCruce
    End Function

    Public Function darPosicion() As Double
        Return posicion
    End Function

    Public Function hayPresencia() As Boolean
        Dim presencia As Boolean = detector.AttValue("PRESENCE")
        'Console.WriteLine("Sensor[" + nombre + "].hayPresencia() = " + presencia.ToString)
        Return presencia
    End Function

    Public Function darVelocidad() As Double
        Dim velocidad As Double = detector.AttValue("VELOCITY")
        'Console.WriteLine("Sensor[" + nombre + "].darVelocidad() = " + velocidad.ToString)
        Return velocidad
    End Function

    Public Function darTiempoEspera() As Double
        Return detector.AttValue("OCCUPANCY")
    End Function
End Class
