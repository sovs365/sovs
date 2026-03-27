# SOVS App Build and Verification Script (PowerShell)
# This script builds the app and verifies the APK is ready for installation

function Show-Menu {
    Write-Host ""
    Write-Host "========================================"
    Write-Host "    SOVS APP - BUILD & VERIFY TOOL"
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "1. Build Debug APK (Recommended)" -ForegroundColor Green
    Write-Host "2. Clean and Build Debug APK"
    Write-Host "3. Verify APK exists"
    Write-Host "4. Show APK location"
    Write-Host "5. Show APK file size"
    Write-Host "6. Clean only"
    Write-Host "0. Exit"
    Write-Host ""
}

function Build-Debug {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Building Debug APK..."
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    & .\gradlew.bat assembleDebug
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "✓ Build SUCCESSFUL!" -ForegroundColor Green
        Verify-APK
    } else {
        Write-Host ""
        Write-Host "✗ Build FAILED!" -ForegroundColor Red
        Write-Host "Check the errors above."
    }
}

function Clean-Build {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Cleaning and Building Debug APK..."
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    & .\gradlew.bat clean assembleDebug
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "✓ Build SUCCESSFUL!" -ForegroundColor Green
        Verify-APK
    } else {
        Write-Host ""
        Write-Host "✗ Build FAILED!" -ForegroundColor Red
        Write-Host "Check the errors above."
    }
}

function Verify-APK {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Verifying APK..."
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    $apkPath = "app\build\outputs\apk\debug\app-debug.apk"
    
    if (Test-Path $apkPath) {
        Write-Host "✓ APK exists!" -ForegroundColor Green
        Write-Host ""
        
        $currentDir = (Get-Location).Path
        Write-Host "Location: $currentDir\$apkPath" -ForegroundColor Cyan
        Write-Host ""
        
        $file = Get-Item $apkPath
        $sizeInMB = $file.Length / 1MB
        Write-Host "File Size: $([math]::Round($sizeInMB, 2)) MB" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "✓ APK is ready for installation!" -ForegroundColor Green
    } else {
        Write-Host "✗ APK not found!" -ForegroundColor Red
        Write-Host "Expected location: app\build\outputs\apk\debug\app-debug.apk"
    }
}

function Show-Location {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "APK Location"
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    $currentDir = (Get-Location).Path
    Write-Host "Full Path:" -ForegroundColor Cyan
    Write-Host "$currentDir\app\build\outputs\apk\debug\app-debug.apk"
    Write-Host ""
    Write-Host "Short Path:" -ForegroundColor Cyan
    Write-Host "SOVS\app\build\outputs\apk\debug\app-debug.apk"
    Write-Host ""
}

function Show-Size {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "APK File Size"
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    $apkPath = "app\build\outputs\apk\debug\app-debug.apk"
    
    if (Test-Path $apkPath) {
        $file = Get-Item $apkPath
        $sizeInBytes = $file.Length
        $sizeInMB = $sizeInBytes / 1MB
        
        Write-Host "File Size: $sizeInBytes bytes" -ForegroundColor Cyan
        Write-Host "File Size: ~$([math]::Round($sizeInMB, 2)) MB" -ForegroundColor Cyan
    } else {
        Write-Host "APK not found. Please build it first." -ForegroundColor Red
    }
    Write-Host ""
}

function Clean-Only {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Cleaning build artifacts..."
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    & .\gradlew.bat clean
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "✓ Clean SUCCESSFUL!" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "✗ Clean FAILED!" -ForegroundColor Red
    }
}

# Main script
Clear-Host
Write-Host "SOVS App - Build & Verification Tool" -ForegroundColor Cyan

# Check if in correct directory
if (-not (Test-Path "gradlew.bat")) {
    Write-Host ""
    Write-Host "ERROR: gradlew.bat not found!" -ForegroundColor Red
    Write-Host "Make sure you're in the SOVS root directory" -ForegroundColor Red
    exit 1
}

Write-Host "Current directory: $(Get-Location)" -ForegroundColor Yellow
Write-Host ""

do {
    Show-Menu
    $choice = Read-Host "Enter your choice (0-6)"
    
    switch ($choice) {
        "1" { Build-Debug }
        "2" { Clean-Build }
        "3" { Verify-APK }
        "4" { Show-Location }
        "5" { Show-Size }
        "6" { Clean-Only }
        "0" { Write-Host "Goodbye!" -ForegroundColor Cyan; exit 0 }
        default { Write-Host "Invalid choice!" -ForegroundColor Red }
    }
    
    Write-Host ""
    Read-Host "Press Enter to continue"
    Clear-Host
    
} while ($true)
