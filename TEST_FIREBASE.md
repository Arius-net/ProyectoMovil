# 🔧 Test Manual de Firebase

## Problema Actual
El botón de guardar se queda en "Guardando..." y nunca termina.

## Causas Más Probables

### 1. Firebase no está inicializado correctamente
- **Síntoma:** El código se queda esperando indefinidamente
- **Solución aplicada:** Inicialización explícita en Application.kt

### 2. Usuario no autenticado
- **Síntoma:** getCurrentUserId() retorna null
- **Solución:** Verificar en logs si UID es null

### 3. Permisos de Firestore bloqueando
- **Síntoma:** Operación se ejecuta pero falla silenciosamente
- **Solución:** Actualizar reglas de Firestore

### 4. Timeout o problema de red
- **Síntoma:** La operación nunca termina
- **Solución aplicada:** Timeout de 30 segundos agregado

## Cambios Implementados en Esta Versión

### ✅ Application.kt
- Inicialización explícita de Firebase
- Logging de inicio de app
- Verificación de que Firebase se inicialice antes de Koin

### ✅ NewNoteViewModel.kt
- Agregado `withTimeout(30000L)` - timeout de 30 segundos
- Agregado `withContext(Dispatchers.IO)` - ejecutar en thread de IO
- Logging más detallado con tipos de excepción
- Mensaje de error específico para timeout

### ✅ AuthService.kt
- Inicialización lazy de FirebaseAuth
- Verificación de que el usuario esté autenticado
- Warnings claros cuando UID es null

### ✅ FirestoreService.kt
- Inicialización lazy de Firestore
- Logging de inicialización

### ✅ AndroidManifest.xml
- Agregado permiso ACCESS_NETWORK_STATE

## Qué Esperar Ahora

### Escenario 1: Timeout (30 segundos)
```
D/NewNoteViewModel: === INICIANDO GUARDADO ===
D/NewNoteViewModel: Iniciando guardado de nota de texto: [título]
... (30 segundos) ...
E/NewNoteViewModel: TIMEOUT: La operación tardó más de 30 segundos
D/NewNoteViewModel: === GUARDADO FINALIZADO (isSaving=false) ===
```
**Aparecerá notificación:** "Timeout: La operación tardó demasiado. Verifica tu conexión a internet."

### Escenario 2: Usuario no autenticado
```
D/AuthService: ⚠️ ADVERTENCIA: Usuario NO autenticado (UID es null)
E/NoteRepository: ERROR: Usuario no autenticado
D/NewNoteViewModel: === GUARDADO FINALIZADO (isSaving=false) ===
```
**Aparecerá notificación:** "Error al guardar: Usuario no autenticado"

### Escenario 3: Error de permisos
```
E/FirestoreService: ERROR al guardar en Firestore
    FirebaseFirestoreException: PERMISSION_DENIED
D/NewNoteViewModel: === GUARDADO FINALIZADO (isSaving=false) ===
```
**Aparecerá notificación:** "Error al guardar: PERMISSION_DENIED..."

### Escenario 4: Éxito
```
D/NewNoteViewModel: === INICIANDO GUARDADO ===
D/AuthService: ✓ Usuario autenticado correctamente
D/FirestoreService: Documento agregado con ID: xyz123
D/FirestoreService: === GUARDADO EXITOSO: xyz123 ===
D/NewNoteViewModel: === GUARDADO FINALIZADO (isSaving=false) ===
```
**Aparecerá notificación:** "Nota guardada"

## Instrucciones para Probar

1. **Instala la nueva versión** (se está instalando ahora)

2. **Abre la terminal y ejecuta:**
```powershell
adb logcat -c
adb logcat | Select-String -Pattern "NotAudioApplication|NewNoteViewModel|AuthService|FirestoreService|NoteRepository"
```

3. **En el dispositivo:**
   - Cierra sesión si estás logueado
   - Vuelve a iniciar sesión
   - Ve a "Nueva Nota de Texto"
   - Escribe algo
   - Presiona "Guardar"

4. **Observa los logs:**
   - Deberías ver el progreso completo
   - O verás el error específico con mensaje claro
   - En máximo 30 segundos sabrás qué pasó

5. **La app mostrará notificación:**
   - Éxito: "Nota guardada"
   - Timeout: "Timeout: La operación tardó demasiado..."
   - Usuario no auth: "Error: Usuario no autenticado"
   - Permisos: "Error: PERMISSION_DENIED..."

## Acciones Inmediatas

### Si ves "Usuario no autenticado":
1. Cierra sesión en la app
2. Vuelve a iniciar sesión
3. Intenta guardar de nuevo

### Si ves "Timeout":
1. Verifica que tengas conexión a internet
2. Ve a Firebase Console → Firestore → Rules
3. Cambia las reglas a modo de prueba (ver abajo)

### Si ves "PERMISSION_DENIED":
1. Ve a: https://console.firebase.google.com/project/notapp-b4cb5/firestore/rules
2. Reemplaza con:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if true;  // TEMPORAL - SOLO PARA PRUEBAS
    }
  }
}
```
3. Publica
4. Espera 1 minuto
5. Intenta de nuevo

## Lo Que DEBE Pasar Ahora

✅ El botón NO se quedará en "Guardando..." por más de 30 segundos
✅ Recibirás una notificación con el resultado
✅ Los logs mostrarán exactamente qué falló
✅ `isSaving` volverá a `false` siempre (incluso con error)

## Si Todavía Se Queda en "Guardando..."

Eso significaría que el problema está ANTES del ViewModel, posiblemente:
- El click del botón no está llegando
- La coroutine no se está iniciando
- Hay un deadlock en Koin

En ese caso, copia TODOS los logs desde que abres la app hasta que presionas guardar.

