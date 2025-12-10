@echo off
echo ================================================
echo   Test de Guardado de Notas - NotAudio
echo ================================================
echo.
echo Limpiando logs anteriores...
adb logcat -c

echo.
echo INSTRUCCIONES:
echo 1. Abre la app en tu dispositivo
echo 2. Ve a "Nueva Nota de Texto"
echo 3. Escribe algo y presiona "Guardar"
echo 4. Observa los logs aqui
echo.
echo Presiona Ctrl+C para detener
echo ================================================
echo.

adb logcat | findstr /C:"NotAudioApplication" /C:"NewNoteViewModel" /C:"AuthService" /C:"FirestoreService" /C:"NoteRepository" /C:"INICIANDO" /C:"EXITOSO" /C:"FINALIZADO" /C:"ERROR" /C:"TIMEOUT" /C:"UID"

