@ECHO OFF

SET SERVICE_HOST=http://localhost:8888
REM SET SERVICE_HOST=http://package-d.lternet.edu:8080
REM SET SERVICE_HOST=http://package-s.lternet.edu:8080
REM SET SERVICE_HOST=http://pasta.lternet.edu

SET SCOPE=knb-lter-nin
SET IDENTIFIER=99
SET REVISION=1

curl -X GET "%SERVICE_HOST%/package/data/size/eml/%SCOPE%/%IDENTIFIER%/%REVISION%"
