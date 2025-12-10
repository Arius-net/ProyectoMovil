# Script Simple - Sin ADB
# Instrucciones para ver logs desde Android Studio

Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  Cómo Ver Logs sin ADB" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "❌ ADB no está disponible en esta terminal" -ForegroundColor Red
Write-Host ""
Write-Host "✅ SOLUCIÓN FÁCIL - Usa Android Studio:" -ForegroundColor Green
Write-Host ""
Write-Host "1️⃣  Abre Android Studio" -ForegroundColor White
Write-Host ""
Write-Host "2️⃣  Ve al menú: View > Tool Windows > Logcat" -ForegroundColor White
Write-Host "    (O presiona Alt+6)" -ForegroundColor Gray
Write-Host ""
Write-Host "3️⃣  En la barra de filtro de Logcat, escribe:" -ForegroundColor White
Write-Host "    package:com.sayd.notaudio" -ForegroundColor Cyan
Write-Host ""
Write-Host "4️⃣  Ejecuta la app con el botón Run (▶️)" -ForegroundColor White
Write-Host ""
Write-Host "5️⃣  Verás TODOS los logs aquí en tiempo real" -ForegroundColor White
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "📋 ALTERNATIVA - Instalar Android SDK:" -ForegroundColor Yellow
Write-Host ""
Write-Host "Si prefieres usar la terminal:" -ForegroundColor White
Write-Host "1. Descarga Android Studio desde:" -ForegroundColor White
Write-Host "   https://developer.android.com/studio" -ForegroundColor Cyan
Write-Host ""
Write-Host "2. Durante la instalación, asegúrate de instalar:" -ForegroundColor White
Write-Host "   ☑️ Android SDK" -ForegroundColor Green
Write-Host "   ☑️ Android SDK Platform-Tools" -ForegroundColor Green
Write-Host ""
Write-Host "3. Luego ejecuta: .\monitor.ps1" -ForegroundColor White
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "🚀 PARA PROBAR LA APP AHORA:" -ForegroundColor Green
Write-Host ""
Write-Host "1. Abre Android Studio" -ForegroundColor White
Write-Host "2. Abre este proyecto" -ForegroundColor White
Write-Host "3. Click en Run (▶️)" -ForegroundColor White
Write-Host "4. Ve a Logcat (Alt+6)" -ForegroundColor White
Write-Host "5. Filtra: package:com.sayd.notaudio" -ForegroundColor White
Write-Host "6. Prueba guardar una nota en el dispositivo" -ForegroundColor White
Write-Host "7. Observa los logs en Logcat" -ForegroundColor White
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Presiona cualquier tecla para salir..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

