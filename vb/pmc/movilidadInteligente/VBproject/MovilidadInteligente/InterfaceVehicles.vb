Imports System
Imports System.Threading
Imports System.Windows.Forms

Public Class InterfaceVehicles

    Private Shared inicio As Boolean
    Private Shared modulo As ModuloEntrada
    Private Shared inter As InterfaceVehicles
    Private Shared cajas As ArrayList
    Private Shared labelt As ArrayList
    Private Shared panel As Panel
    Private thread As New Thread(AddressOf ThreadProc)
    Private thread2 As New Thread(AddressOf ThreadProc2)

    Public Shared Sub ThreadProc2()
        modulo = New ModuloEntrada
        modulo.Inicializar(inter)
        'Do
        '    Console.Write("Cerrar consola? (s/n) ")
        'Loop Until [String].Equals(Console.ReadLine(), "s")
    End Sub

    Public Shared Sub ThreadProc()
        modulo.Simular(cajas)
        'Do
        '    Console.Write("Cerrar consola? (s/n) ")
        'Loop Until [String].Equals(Console.ReadLine(), "s")
    End Sub

    Public Sub New()
        ' This call is required by the designer.
        InitializeComponent()
        inicio = False
        inter = Me
        panel = Panel1
        cajas = New ArrayList
        labelt = New ArrayList
        thread2.Start()
    End Sub

    Private Sub InterfaceVehicles_Load(sender As System.Object, e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub Button1_Click1(sender As Object, e As System.EventArgs) Handles Button1.Click
        ' Add any initialization after the InitializeComponent() call.
        If Not inicio Then
            inicio = True
            Button1.Enabled = False
            ' Start ThreadProc.  Note that on a uniprocessor, the new  
            ' thread does not get any processor time until the main thread  
            ' is preempted or yields.  Uncomment the Thread.Sleep that  
            ' follows t.Start() to see the difference.
            thread.Start()
        End If


    End Sub

    Private Sub Panel1_Paint(sender As System.Object, e As System.Windows.Forms.PaintEventArgs) Handles Panel1.Paint

    End Sub

    Public Function definirCantidadCarros(carros As Integer)
        Dim i As Integer = 0
        While (i < carros)
            If panel.InvokeRequired Then
                Dim d As New ContextCallback(AddressOf definirCantidadCarros)
                panel.Invoke(d, New Object() {carros - (carros - 1)})
            Else
                Dim txt As New TextBox
                cajas.Add(txt)
                txt.Name = cajas.Count
                txt.Width = 50
                txt.Text = 1000

                Dim titulo As New Label
                labelt.Add(titulo)
                titulo.Name = cajas.Count.ToString + "label"
                titulo.Text = "Carril: " + cajas.Count.ToString
                titulo.Width = 80
                panel.Controls.Add(titulo)
                panel.Controls.Add(txt)
            End If
            i += 1
        End While
        Return cajas
    End Function

    Private Sub Panel1_Paint_1(sender As System.Object, e As System.Windows.Forms.PaintEventArgs) Handles Panel1.Paint

    End Sub

    Private Sub Button2_Click(sender As System.Object, e As System.EventArgs) Handles Button2.Click
        thread.Abort()
    End Sub

    
   

    Private Sub TextBox1_LostFocus(sender As Object, e As System.EventArgs) Handles TextBox1.LostFocus
        For Each box As TextBox In cajas
            box.Text = TextBox1.Text
        Next
    End Sub

    Function definirnombreCarros(carros As Integer) As ArrayList
        'Dim i As Integer = 0
        'While (i < carros)
        '    If panel.InvokeRequired Then
        '        Dim d As New ContextCallback(AddressOf definirnombreCarros)
        '        panel.Invoke(d, New Object() {carros - (carros - 1)})
        '    Else
        '    End If
        '    i += 1
        'End While
        Return labelt
    End Function

End Class