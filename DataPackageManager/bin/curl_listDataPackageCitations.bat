@ECHO OFF
SET SERVICE_HOST=http://localhost:8888
SET SCOPE=edi
SET IDENTIFIER=0
SET REVISION=3

curl -i -G %SERVICE_HOST%/package/citations/eml/%SCOPE%/%IDENTIFIER%/%REVISION%
