@ECHO OFF
ECHO DataPackageManager-0.1 test suite for use on local Windows 7 development workstation 

SET SERVICE_HOST=http://localhost:8080
SET AUTH_TOKEN_UCARROLL=dWlkPXVjYXJyb2xsLG89TFRFUixkYz1lY29pbmZvcm1hdGljcyxkYz1vcmcqaHR0cHM6Ly9wYXN0YS5sdGVybmV0LmVkdS9hdXRoZW50aWNhdGlvbioyMDAwMDAwMDAwKmF1dGhlbnRpY2F0ZWQ=
SET AUTH_TOKEN_DCOSTA=dWlkPWRjb3N0YSxvPUxURVIsZGM9ZWNvaW5mb3JtYXRpY3MsZGM9b3JnKmh0dHBzOi8vcGFzdGEubHRlcm5ldC5lZHUvYXV0aGVudGljYXRpb24qMjAwMDAwMDAwMCphdXRoZW50aWNhdGVk
SET SCOPE=knb-lter-xyz
SET IDENTIFIER=10044
SET REVISION=9
SET TRANSACTION=1364424858431

ECHO Read an Evaluate Report
curl -i -b auth-token=%AUTH_TOKEN_UCARROLL% -G %SERVICE_HOST%/package/evaluate/report/eml/%SCOPE%/%IDENTIFIER%/%REVISION%/%TRANSACTION%
REM curl -i -b auth-token=%AUTH_TOKEN_UCARROLL% -H "Accept: text/html" -G %SERVICE_HOST%/package/evaluate/report/eml/%SCOPE%/%IDENTIFIER%/%REVISION%/%TRANSACTION%
