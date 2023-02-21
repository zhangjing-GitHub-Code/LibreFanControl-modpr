$serviceName = "FanControlService"
$startMode = $args[0]


if ($startMode -eq "manual") {
    if (Test-Path "HKLM:\SYSTEM\CurrentControlSet\Services\$serviceName") {
        Remove-Item -Path "HKLM:\SYSTEM\CurrentControlSet\Services\$serviceName" -Force -Recurse
        Write-Output "Registry key for $serviceName has been removed."
    }
}

Set-Service -Name $serviceName -StartupType $startMode

$serviceStatus = (Get-Service -Name $serviceName).Status
Write-Output "$serviceName status: $serviceStatus"



