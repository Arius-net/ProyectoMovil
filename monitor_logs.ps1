# Script para monitorear logs de NotAudio en tiempo real
# Ejecuta este script en PowerShell mientras usas la app

Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "  Monitor de Logs - NotAudio App" -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Limpiando logs anteriores..." -ForegroundColor Yellow
adb logcat -c

Write-Host "Iniciando monitoreo de logs..." -ForegroundColor Green
Write-Host ""
Write-Host "INSTRUCCIONES:" -ForegroundColor White
Write-Host "1. Deja esta ventana abierta" -ForegroundColor White
Write-Host "2. Abre la app en tu dispositivo" -ForegroundColor White
Write-Host "3. Intenta guardar una nota" -ForegroundColor White
Write-Host "4. Observa los logs aquí" -ForegroundColor White
Write-Host ""
Write-Host "Presiona Ctrl+C para detener..." -ForegroundColor Yellow
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""

# Monitorear logs relevantes con colores
adb logcat | ForEach-Object {
    if ($_ -match "NewNoteViewModel|NoteRepository|FirestoreService|AuthService|HomeViewModel") {

        # Colorear según el tipo de log
        if ($_ -match "ERROR|Exception|FAILED|crash") {
            Write-Host $_ -ForegroundColor Red
        }
        elseif ($_ -match "EXITOSO|SUCCESS|guardada exitosamente") {
            Write-Host $_ -ForegroundColor Green
        }
        elseif ($_ -match "WARNING|WARN") {
            Write-Host $_ -ForegroundColor Yellow
        }
        elseif ($_ -match "INICIANDO|llamado|obtenido") {
            Write-Host $_ -ForegroundColor Cyan
        }
        else {
            Write-Host $_ -ForegroundColor White
        }
    }
}

