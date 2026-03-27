@echo off
REM SOVS App Build and Verification Script
REM This script builds the app and verifies the APK is ready for installation

echo.
echo ========================================
echo      SOVS APP - BUILD & VERIFY TOOL
echo ========================================
echo.

REM Get current directory
cd /d "%~dp0"

REM Check if gradlew exists
if not exist "gradlew.bat" (
    echo ERROR: gradlew.bat not found!
    echo Make sure you're in the SOVS root directory
    pause
    exit /b 1
)

echo [INFO] Current directory: %cd%
echo.

REM Menu
echo Select an option:
echo.
echo 1. Build Debug APK (Recommended)
echo 2. Clean and Build Debug APK
echo 3. Verify APK exists
echo 4. Show APK location
echo 5. Show APK file size
echo 6. Clean only
echo 0. Exit
echo.

set /p choice="Enter your choice (0-6): "

if "%choice%"=="1" (
    call :build_debug
) else if "%choice%"=="2" (
    call :clean_build
) else if "%choice%"=="3" (
    call :verify_apk
) else if "%choice%"=="4" (
    call :show_location
) else if "%choice%"=="5" (
    call :show_size
) else if "%choice%"=="6" (
    call :clean_only
) else if "%choice%"=="0" (
    echo Goodbye!
    exit /b 0
) else (
    echo Invalid choice!
    pause
    exit /b 1
)

pause
exit /b 0

:build_debug
echo.
echo ========================================
echo Building Debug APK...
echo ========================================
echo.
call gradlew.bat assembleDebug
if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✓ Build SUCCESSFUL!
    call :verify_apk
) else (
    echo.
    echo ✗ Build FAILED!
    echo Check the errors above.
)
exit /b %ERRORLEVEL%

:clean_build
echo.
echo ========================================
echo Cleaning and Building Debug APK...
echo ========================================
echo.
call gradlew.bat clean assembleDebug
if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✓ Build SUCCESSFUL!
    call :verify_apk
) else (
    echo.
    echo ✗ Build FAILED!
    echo Check the errors above.
)
exit /b %ERRORLEVEL%

:verify_apk
echo.
echo ========================================
echo Verifying APK...
echo ========================================
echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo ✓ APK exists!
    echo.
    echo Location: %cd%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    for %%A in (app\build\outputs\apk\debug\app-debug.apk) do (
        set /a size=%%~zA / 1048576
        echo File Size: %%~zA bytes (~%size% MB)
    )
    echo.
    echo ✓ APK is ready for installation!
) else (
    echo ✗ APK not found!
    echo Expected location: app\build\outputs\apk\debug\app-debug.apk
)
exit /b 0

:show_location
echo.
echo ========================================
echo APK Location
echo ========================================
echo.
echo Full Path:
echo %cd%\app\build\outputs\apk\debug\app-debug.apk
echo.
echo Short Path:
echo SOVS\app\build\outputs\apk\debug\app-debug.apk
echo.
exit /b 0

:show_size
echo.
echo ========================================
echo APK File Size
echo ========================================
echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    for %%A in (app\build\outputs\apk\debug\app-debug.apk) do (
        set /a size=%%~zA / 1048576
        echo File Size: %%~zA bytes (~%size% MB)
    )
) else (
    echo APK not found. Please build it first.
)
exit /b 0

:clean_only
echo.
echo ========================================
echo Cleaning build artifacts...
echo ========================================
echo.
call gradlew.bat clean
if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✓ Clean SUCCESSFUL!
) else (
    echo.
    echo ✗ Clean FAILED!
)
exit /b %ERRORLEVEL%
