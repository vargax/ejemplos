function Get-SharedFiles {
    param (
        [string] $inputFile = ".\shares.txt",
        [string] $outputFile = ".\sharedFiles.csv",
        [string] $resultsFile = ".\results.csv"
    )
    
    [string] $OFFLINE = "Offline"
    [string] $ACCESS_DENIED = "Access Denied"
    [string] $DONE = "Done"

    function logResult {
        param (
            [string] $statusCode,
            [string] $share  
        )
        $results[$statusCode] += 1

        $activity = "Audit in progress $currentShare/$totalShares"
        foreach ($key in $results.GetEnumerator()) {
            $activity += " | $($key.Name) $($key.Value) "
        }
        Write-Progress -Activity $activity -Status "$statusCode $share" -PercentComplete (100*($currentShare/$totalShares))

        Add-Content -Path $resultsFile -Value "$statusCode,$share"
        Write-Host " $statusCode"
    }
  
    $shares = Get-Content $inputFile
    [int] $totalShares = $shares.Length
    [int] $currentShare = 0
    $results =@{
        $OFFLINE = 0
        $ACCESS_DENIED = 0
        $DONE = 0
    }

    Write-Progress -Activity "Audit in progress ($currentShare/$totalShares)"
    ForEach ($share in $shares) {
        #sequence {
            $currentShare += 1
            $split = $share.Split("\",[System.StringSplitOptions]'RemoveEmptyEntries')
        
            Write-Host -NoNewline $split[0]" ping..."
            if (Test-Connection -ComputerName $split[0] -Count 1 -Quiet) {
                Write-Host -NoNewline " quering "$split[1]"..."      
                $files = Get-ChildItem -Recurse -Depth 1 $share -ErrorAction SilentlyContinue
                
                if ($files) {
                    $files | Select-Object FullName, Extension, IsReadOnly, Length, CreationTime, LastAccessTime, LastWriteTime, Attributes | Export-CSV $outputFile -Append -NoTypeInformation
                    $statusCode = $DONE
                    logResult $statusCode $share
                } else {
                    $statusCode = $ACCESS_DENIED
                    logResult $statusCode $share
                }
            } else {
                $statusCode = $OFFLINE
                logResult $statusCode $share
            }
        #}
    }
}

Get-SharedFiles