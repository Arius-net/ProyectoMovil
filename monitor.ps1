# Monitor de Logs para NotAudio App
# Este script usa el ADB incluido con Android Studio

Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  Monitor de Logs - NotAudio" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Posibles ubicaciones de ADB
$adbLocations = @(
    "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe",
    "$env:USERPROFILE\AppData\Local\Android\Sdk\platform-tools\adb.exe",
    "C:\Android\Sdk\platform-tools\adb.exe",
    "C:\Program Files (x86)\Android\android-sdk\platform-tools\adb.exe"
)

$adbPath = $null

# Buscar ADB
foreach ($location in $adbLocations) {
    if (Test-Path $location) {
        $adbPath = $location
        Write-Host "✓ ADB encontrado en: $location" -ForegroundColor Green
        break
    }
}

# Si no se encuentra, buscar en todo el sistema
if (-not $adbPath) {
    Write-Host "Buscando ADB en el sistema (esto puede tardar)..." -ForegroundColor Yellow
    $found = Get-ChildItem -Path "C:\" -Filter "adb.exe" -Recurse -ErrorAction SilentlyContinue | Where-Object { $_.FullName -like "*platform-tools*" } | Select-Object -First 1

    if ($found) {
        $adbPath = $found.FullName
        Write-Host "✓ ADB encontrado en: $adbPath" -ForegroundColor Green
    }
}

# Si aún no se encuentra, intentar usar gradlew
if (-not $adbPath) {
    Write-Host "" -ForegroundColor Red
    Write-Host "❌ ERROR: No se encontró ADB" -ForegroundColor Red
    Write-Host "" -ForegroundColor Yellow
    Write-Host "SOLUCIONES:" -ForegroundColor Yellow
    Write-Host "1. Instala Android Studio desde: https://developer.android.com/studio" -ForegroundColor White
    Write-Host "2. O usa Android Studio para ver los logs (View > Tool Windows > Logcat)" -ForegroundColor White
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Presiona cualquier tecla para salir..." -ForegroundColor Gray
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
    exit 1
}

Write-Host ""
Write-Host "🔍 Verificando dispositivos conectados..." -ForegroundColor Cyan
& $adbPath devices
Write-Host ""

# Verificar si hay dispositivos conectados
$devices = & $adbPath devices | Select-String "device$"
if (-not $devices) {
    Write-Host "⚠️ ADVERTENCIA: No hay dispositivos conectados" -ForegroundColor Yellow
    Write-Host "Conecta tu dispositivo Android o inicia un emulador" -ForegroundColor White
    Write-Host ""
    Write-Host "Presiona cualquier tecla para salir..." -ForegroundColor Gray
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
    exit 1
}

Write-Host "🗑️ Limpiando logs antiguos..." -ForegroundColor Cyan
& $adbPath logcat -c
Start-Sleep -Milliseconds 500

Write-Host ""
Write-Host "📊 MONITOR INICIADO" -ForegroundColor Green
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "INSTRUCCIONES:" -ForegroundColor White
Write-Host "1. Abre la app NotAudio en tu dispositivo" -ForegroundColor White
Write-Host "2. Cierra sesion y vuelve a iniciar sesion" -ForegroundColor White
Write-Host "3. Ve a Nueva Nota de Texto" -ForegroundColor White
Write-Host "4. Escribe algo y presiona Guardar" -ForegroundColor White
Write-Host "5. Observa los logs aqui (se mostraran en colores)" -ForegroundColor White
Write-Host ""
Write-Host "Presiona Ctrl+C para detener el monitor" -ForegroundColor Yellow
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Iniciar monitor con colores
try {
    & $adbPath logcat | ForEach-Object {
        $line = $_

        # Filtrar solo líneas relevantes
        if ($line -match "NotAudioApplication|NewNoteViewModel|AuthService|FirestoreService|NoteRepository") {

            # Colorear según el tipo de log
            if ($line -match "ERROR|Exception|FAILED|crash|FirebaseFirestoreException") {
                Write-Host $line -ForegroundColor Red
            }
            elseif ($line -match "EXITOSO|SUCCESS|guardada exitosamente|✓") {
                Write-Host $line -ForegroundColor Green
            }
            elseif ($line -match "WARNING|WARN|ADVERTENCIA|⚠️") {
                Write-Host $line -ForegroundColor Yellow
            }
            elseif ($line -match "INICIANDO|FINALIZADO|llamado|obtenido") {
                Write-Host $line -ForegroundColor Cyan
            }
            elseif ($line -match "TIMEOUT") {
                Write-Host $line -ForegroundColor Magenta
            }
            else {
                Write-Host $line -ForegroundColor White
            }
        }
    }
}
catch {
    Write-Host ""
    Write-Host "Monitor detenido." -ForegroundColor Yellow
}

