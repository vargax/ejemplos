Attribute VB_Name = "MailMerge"
' Outlook setup
Const oSUBJECT As String = "ITE/IPP Annual Local Admin Audit"

' Excel file
Const FILE_NAME As String = "mailMerge.xlsx"

Const xComputerName = "A"
Const xMemberName = "B"
Const xFirstName = "D"
Const xEmail = "G"

Const LEFT_COLUMN = xComputerName
Const RIGHT_COLUMN = xEmail

' Word variables
Const wFirstName = "firstName"
Const wMemberName = "memberName"
Const wHostsParagraph = 5

' Attributes
Private excelApp As Excel.Application
Private excelWorkbook As Excel.Workbook
Private excelWorksheet As Excel.Worksheet

Private outlookApp As Outlook.Application

Function init()
    On Error Resume Next
    Set excelApp = GetObject(, "Excel.Application")
    If Err Then
        Set excelApp = New Excel.Application
    End If
    
    On Error Resume Next
    Set outlookApp = GetObject(, "Outlook.Application")
    If Err Then
        Set outlookApp = New Outlook.Application
    End If
    
End Function

Sub main()
    init
    Application.ScreenUpdating = True
    xLoadSheet
    
    Dim myRange As Excel.range
    Set myRange = xGetRange
    
    currentRow = 2: totalRows = myRange.Rows.Count 'Start on 2 to skip headers
    While currentRow < totalRows
        
        ' Write greeting and introduction
        ActiveDocument.Variables(wFirstName).Value = myRange(currentRow, xFirstName).Value
        Dim userEmail, memberName, nextMemberName, hostNames As String
        userEmail = myRange(currentRow, xEmail)
        memberName = myRange(currentRow, xMemberName).Value
        ActiveDocument.Variables(wMemberName).Value = memberName
        ActiveDocument.Fields.Update
        
        ' Write bullet list
        wClearBullets
        nextMemberName = memberName: hostNames = "": countHostNames = 0
        While memberName = nextMemberName
            countHostNames = countHostNames + 1
            hostNames = hostNames & myRange(currentRow, xComputerName).Value & vbCr
            currentRow = currentRow + 1
            nextMemberName = myRange(currentRow, xMemberName).Value
        Wend
        
        ActiveDocument.Paragraphs(wHostsParagraph - 1).range.InsertAfter hostNames
        wGetRange(wHostsParagraph, wHostsParagraph + countHostNames).ListFormat.ApplyBulletDefault
        
        ' Create email
        ActiveDocument.content.Copy
        oCreateMail (userEmail)
        
    Wend
End Sub

Function oCreateMail(oTo As String)
    Dim oDraft As Outlook.MailItem
    Set oDraft = Outlook.Application.CreateItem(olMailItem)
    With oDraft
        .To = oTo
        .SUBJECT = oSUBJECT
        .Display
    End With
    
    Dim oEditor As Document
    Set oEditor = oDraft.GetInspector.WordEditor
    
    Dim oRange As Word.range
    Set oRange = oEditor.range(0)
    oRange.Collapse
    oRange.PasteAndFormat wdFormatOriginalFormatting
    
    oDraft.Recipients.ResolveAll
    oDraft.Close olSave
    
End Function

Function wClearBullets()
    Dim currentParagraph As Integer: currentParagraph = 1
    While Not (ActiveDocument.Paragraphs(currentParagraph).range.ListFormat.ListType = wdListBullet)
        currentParagraph = currentParagraph + 1
    Wend
    
    While (ActiveDocument.Paragraphs(currentParagraph).range.ListFormat.ListType = wdListBullet)
        ActiveDocument.Paragraphs(currentParagraph).range.Delete
    Wend
        
End Function

Function wGetRange(startParagraph As Integer, endParagraph As Integer) As range

    rangeStart = ActiveDocument.Paragraphs(startParagraph).range.Start
    rangeEnd = ActiveDocument.Paragraphs(endParagraph + countHostNames).range.Start
    Set wGetRange = ActiveDocument.range(rangeStart, rangeEnd)

End Function

Function xGetRange() As Excel.range

    Set xGetRange = excelWorksheet.range(LEFT_COLUMN + "1", excelWorksheet.range(RIGHT_COLUMN + "1").End(xlDown))

End Function

Function xLoadSheet()
    
    filePath = Application.ActiveDocument.Path & "\" & FILE_NAME
    
    Set excelWorkbook = excelApp.Workbooks.Open(filePath)
    excelApp.Visible = True
    excelApp.ScreenUpdating = True
    
    Set excelWorksheet = excelWorkbook.Worksheets(1)
    excelWorksheet.Activate
    
    ' Data must be sorted by the grouping criteria
    With excelWorksheet.Sort
        .SortFields.Add Key:=excelWorksheet.range(xMemberName + "1"), Order:=xlAscending
        .SortFields.Add Key:=excelWorksheet.range(xComputerName + "1"), Order:=xlDescending
        .setRange xGetRange
        .Header = xlYes
        .Apply
    End With
End Function
