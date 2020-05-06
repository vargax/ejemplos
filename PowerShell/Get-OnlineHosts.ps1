function Get-OnlineHosts {
  param (
      [string] $inputFile = ".\hosts.txt",
      [string] $resultsFile = ".\results.csv"
  )
  
  [string] $OFFLINE = "Offline"
  [string] $ONLINE = "Online"

  function logResult {
      param (
          [string] $statusCode,
          [string] $hostName  
      )
      $results[$statusCode] += 1

      $activity = "Audit in progress $currentHost/$totalHosts"
      foreach ($key in $results.GetEnumerator()) {
          $activity += " | $($key.Name) $($key.Value) "
      }
      Write-Progress -Activity $activity -Status "$statusCode $hostName" -PercentComplete (100*($currentHost/$totalHosts))

      Add-Content -Path $resultsFile -Value "$statusCode,$hostName"
      Write-Host " $statusCode"
  }

  $hosts = Get-Content $inputFile
  [int] $totalHosts = $hosts.Length
  [int] $currentHost = 0
  $results =@{
      $OFFLINE = 0
      $ONLINE = 0
  }

  Write-Progress -Activity "Check in progress ($currentHost/$totalHosts)"
  ForEach ($hostName in $hosts) {
    $currentHost += 1  
    Write-Host -NoNewline $hostName" ping..."

    if (Test-Connection -ComputerName $hostName -Count 1 -Quiet) {
      $statusCode = $ONLINE  
    } else {
      $statusCode = $OFFLINE  
    }

    logResult $statusCode $hostName
  }
}

Get-OnlineHosts