@echo off
REM ktlint wrapper script for Windows
setlocal enabledelayedexpansion

REM Set JAVA_HOME if not already set
if not defined JAVA_HOME (
    set "JAVA_HOME=C:\Program Files\Microsoft\jdk-21.0.10.7-hotspot"
)

REM Get the directory where this script is located
set "SCRIPT_DIR=%~dp0"

REM Run ktlint
"%JAVA_HOME%\bin\java.exe" --add-opens=java.base/java.lang=ALL-UNNAMED -jar "%SCRIPT_DIR%ktlint\ktlint-1.8.0\bin\ktlint" %*

endlocal
