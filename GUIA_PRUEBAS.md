# 🧪 Guía de Pruebas - NotAudio App

## 📋 Plan de Pruebas Completo

### ✅ PREREQUISITOS

Antes de empezar:
1. ✅ La app debe estar instalada en el dispositivo/emulador
2. ✅ Debes tener una cuenta de usuario y estar autenticado
3. ✅ Conexión a internet activa (para Firebase)
4. ✅ Permisos de micrófono concedidos (para notas de voz)

---

## 🧪 TEST 1: Notas de Texto - Crear y Visualizar

### Objetivo
Verificar que las notas de texto se crean, guardan y muestran correctamente.

### Pasos
1. **Abrir la app**
   - ✅ Debe mostrarse HomeScreen sin crashes
   - ✅ Debe aparecer la frase del día
   - ✅ Botones "Nueva Nota de Texto" y "Nueva Nota de Voz" visibles

2. **Crear nota de texto**
   - Toca **"Nueva Nota de Texto"**
   - ✅ Debe navegar a NewTextNoteScreen
   - Escribe en el campo "Título": `"Mi primera nota de texto"`
   - Escribe en el campo "Contenido": `"Este es el contenido de mi primera nota. Funciona perfectamente!"`
   - ✅ Contador de caracteres debe actualizarse
   - Toca **"Guardar"**

3. **Verificar navegación**
   - ✅ Debe regresar a HomeScreen automáticamente
   - ✅ NO debe haber crash
   - ✅ Debe aparecer una notificación: "Nota guardada"

4. **Verificar HomeScreen**
   - ✅ La nota debe aparecer en la lista "Mis Notas"
   - ✅ Título debe ser: "Mi primera nota de texto"
   - ✅ Subtítulo debe decir: "Nota de texto"
   - ✅ Contador debe decir: "1 notas"
   - ✅ Botón "Eliminar" debe estar visible

5. **Verificar AllNotesScreen**
   - Toca el icono de **"Notas"** en la barra inferior (3er icono)
   - ✅ Debe navegar a AllNotesScreen
   - ✅ Título debe decir: "Todas las Notas"
   - ✅ Contador debe decir: "1 notas"
   - ✅ La nota debe aparecer en la lista
   - ✅ Card debe tener icono de documento (📄) morado
   - ✅ Debe mostrar un preview del contenido

6. **Verificar filtro "Texto"**
   - Toca el tab **"Texto"**
   - ✅ Debe mostrar: "Texto (1)"
   - ✅ La nota debe seguir visible
   - Toca el tab **"Voz"**
   - ✅ Debe mostrar: "Voz (0)"
   - ✅ Debe aparecer mensaje: "No tienes notas"

### Resultado Esperado
- ✅ Nota creada exitosamente
- ✅ Visible en HomeScreen
- ✅ Visible en AllNotesScreen
- ✅ Filtros funcionan correctamente

---

## 🎤 TEST 2: Notas de Voz - Crear y Visualizar

### Objetivo
Verificar que las notas de voz se graban, suben a Firebase y muestran correctamente.

### Pasos
1. **Navegar a HomeScreen**
   - Desde AllNotesScreen, toca el icono **"Inicio"** (1er icono)

2. **Crear nota de voz**
   - Toca **"Nueva Nota de Voz"**
   - ✅ Debe navegar a NewVoiceNoteScreen
   - Escribe en el campo "Título": `"Mi primera grabación"`

3. **Grabar audio**
   - Toca **"Iniciar Grabación"**
   - ✅ Si es la primera vez, debe solicitar permiso de micrófono → **Acepta**
   - ✅ Debe cambiar el texto a: "Grabando... 0s"
   - ✅ El timer debe incrementarse cada segundo
   - **Habla durante 5-10 segundos** (di algo como: "Hola, esta es mi primera nota de voz para probar la aplicación")
   - ✅ Timer debe mostrar: "Grabando... 5s", "Grabando... 6s", etc.

4. **Detener grabación**
   - Toca **"Detener"**
   - ✅ Debe cambiar el texto a: "Grabación lista (5s)" (o el tiempo que grabaste)
   - ✅ Botón "Guardar" debe habilitarse (color sólido)

5. **Guardar nota de voz**
   - Toca **"Guardar"**
   - ✅ Botón debe decir: "Guardando..."
   - ✅ Debe estar deshabilitado durante la subida
   - ⏳ **Espera** (puede tardar 2-5 segundos en subir el audio)

6. **Verificar navegación**
   - ✅ Debe regresar a HomeScreen automáticamente
   - ✅ NO debe haber crash
   - ✅ Debe aparecer una notificación: "Nota guardada"

7. **Verificar HomeScreen**
   - ✅ La nota de voz debe aparecer en la lista
   - ✅ Título debe ser: "Mi primera grabación"
   - ✅ Subtítulo debe decir: "Nota de voz • 5s" (o la duración que grabaste)
   - ✅ Icono de micrófono visible
   - ✅ Contador debe decir: "2 notas" (si ya habías creado la nota de texto)

8. **Verificar AllNotesScreen**
   - Toca el icono de **"Notas"** en la barra inferior
   - ✅ Contador debe decir: "2 notas"
   - ✅ La nota de voz debe aparecer
   - ✅ Card debe tener icono de micrófono (🎤) rosa
   - ✅ Debe mostrar: "Nota de voz • 5s"

9. **Verificar filtros**
   - Toca el tab **"Voz"**
   - ✅ Debe mostrar: "Voz (1)"
   - ✅ Solo debe aparecer la nota de voz
   - Toca el tab **"Texto"**
   - ✅ Debe mostrar: "Texto (1)"
   - ✅ Solo debe aparecer la nota de texto
   - Toca el tab **"Todas"**
   - ✅ Debe mostrar: "Todas (2)"
   - ✅ Deben aparecer ambas notas

### Resultado Esperado
- ✅ Audio grabado exitosamente
- ✅ Audio subido a Firebase Storage
- ✅ Metadatos guardados en Firestore
- ✅ Nota visible en HomeScreen
- ✅ Nota visible en AllNotesScreen
- ✅ Filtros funcionan correctamente

---

## 🔍 TEST 3: Búsqueda de Notas

### Objetivo
Verificar que la búsqueda funciona correctamente.

### Pasos
1. **Crear varias notas** (si no las tienes ya)
   - Crea 2-3 notas de texto con títulos diferentes
   - Crea 2-3 notas de voz con títulos diferentes

2. **Buscar en HomeScreen**
   - En HomeScreen, toca la barra de búsqueda
   - Escribe: `"primera"`
   - ✅ Debe filtrar y mostrar solo las notas que contengan "primera" en el título o contenido

3. **Buscar en AllNotesScreen**
   - Ve a **"Todas las Notas"**
   - En la barra de búsqueda, escribe: `"grabación"`
   - ✅ Solo debe aparecer la nota de voz: "Mi primera grabación"
   - Borra el texto de búsqueda
   - ✅ Deben aparecer todas las notas de nuevo

### Resultado Esperado
- ✅ Búsqueda funciona en ambas pantallas
- ✅ Filtra por título y contenido
- ✅ Combinación de búsqueda + filtros funciona

---

## 🗑️ TEST 4: Eliminar Notas

### Objetivo
Verificar que las notas se eliminan correctamente de Firebase.

### Pasos
1. **Eliminar desde HomeScreen**
   - En HomeScreen, encuentra cualquier nota
   - Toca el botón **"Eliminar"** (rojo)
   - ✅ La nota debe desaparecer inmediatamente
   - ✅ Contador debe decrementar
   - ✅ Si era una nota de voz, el audio debe eliminarse de Storage

2. **Eliminar desde AllNotesScreen**
   - Ve a **"Todas las Notas"**
   - Toca el icono de **basura** en cualquier nota
   - ✅ La nota debe desaparecer
   - ✅ Contador debe actualizarse
   - ✅ Si cambias de filtro y regresas, la nota NO debe aparecer

3. **Verificar persistencia**
   - Elimina todas las notas
   - Cierra la app completamente (Force Stop)
   - Reabre la app
   - ✅ No deben aparecer las notas eliminadas
   - ✅ Debe decir: "Aún no tienes notas"

### Resultado Esperado
- ✅ Notas se eliminan de Firebase
- ✅ UI se actualiza inmediatamente
- ✅ Cambios persisten después de reiniciar

---

## ⏱️ TEST 5: Límite de Grabación (5 minutos)

### Objetivo
Verificar que no se pueda grabar más de 5 minutos.

### Pasos
1. **Crear nota de voz larga**
   - Toca **"Nueva Nota de Voz"**
   - Toca **"Iniciar Grabación"**
   - ✅ Déjalo grabar (puedes silenciarlo o hablar)

2. **Observar timer**
   - ✅ Debe incrementarse: 1s, 2s, 3s...
   - **Espera hasta que llegue a 300s** (5 minutos)
   - ⚠️ **Nota:** Este test es largo, puedes omitirlo y confiar en que funciona

3. **Verificar detención automática**
   - ✅ Al llegar a 300s, debe detenerse automáticamente
   - ✅ Debe aparecer una notificación: "Grabación detenida - Se alcanzó el límite de 5 minutos"
   - ✅ Debe decir: "Grabación lista (300s)"

### Resultado Esperado
- ✅ No se puede grabar más de 5 minutos
- ✅ Detención automática funciona
- ✅ Notificación informativa aparece

---

## 🔄 TEST 6: Sincronización y Navegación

### Objetivo
Verificar que la sincronización con Firebase funciona y la navegación no crashea.

### Pasos
1. **Crear nota desde dispositivo A** (si tienes 2 dispositivos)
   - Crea una nota de texto
   - ✅ Debe guardarse

2. **Abrir app en dispositivo B** (con la misma cuenta)
   - Espera unos segundos
   - ✅ La nota debe aparecer automáticamente
   - ⚠️ **Nota:** Si solo tienes 1 dispositivo, omite este test

3. **Navegar entre pantallas**
   - HomeScreen → AllNotesScreen → HomeScreen
   - ✅ No debe haber crashes
   - HomeScreen → NewTextNoteScreen → Cancelar → HomeScreen
   - ✅ No debe haber crashes
   - HomeScreen → NewVoiceNoteScreen → Cancelar → HomeScreen
   - ✅ No debe haber crashes

4. **Rotar pantalla** (si es móvil, no emulador)
   - Rota el dispositivo
   - ✅ La UI debe adaptarse
   - ✅ No debe crashear
   - ✅ Los datos deben persistir

### Resultado Esperado
- ✅ Sincronización en tiempo real funciona
- ✅ Navegación estable sin crashes
- ✅ Rotación no afecta funcionalidad

---

## 🐛 TEST 7: Manejo de Errores

### Objetivo
Verificar que la app maneja errores correctamente.

### Pasos
1. **Sin internet - Crear nota**
   - Desactiva Wi-Fi y datos móviles
   - Intenta crear una nota de texto
   - Toca **"Guardar"**
   - ✅ Debe aparecer un error: "No se pudo guardar"
   - ⚠️ **Nota:** Puede que la app intente guardar y falle con timeout

2. **Sin internet - Abrir app**
   - Cierra la app
   - Sin internet, reabre la app
   - ✅ Puede que aparezca mensaje de error o lista vacía
   - Reactiva internet
   - ✅ Las notas deben cargarse

3. **Permiso de micrófono denegado**
   - Ve a Configuración → Apps → NotAudio → Permisos
   - Deshabilita **"Micrófono"**
   - Intenta crear una nota de voz
   - Toca **"Iniciar Grabación"**
   - ✅ Debe solicitar permiso de nuevo
   - Si lo niegas:
   - ✅ Debe aparecer notificación: "Permiso denegado"

4. **Nota vacía**
   - Intenta guardar una nota de texto sin escribir nada
   - ✅ Debe aparecer notificación: "La nota no puede estar vacía"

5. **Sin grabación**
   - En NewVoiceNoteScreen, intenta tocar **"Guardar"** sin grabar
   - ✅ Botón debe estar deshabilitado (gris)
   - ✅ No debe hacer nada

### Resultado Esperado
- ✅ Errores se manejan sin crashes
- ✅ Notificaciones informativas aparecen
- ✅ Validaciones funcionan correctamente

---

## 📊 CHECKLIST FINAL

### Funcionalidades Básicas
- [ ] Crear nota de texto
- [ ] Crear nota de voz
- [ ] Ver notas en HomeScreen
- [ ] Ver notas en AllNotesScreen
- [ ] Eliminar notas
- [ ] Buscar notas
- [ ] Filtrar por tipo

### Funcionalidades Avanzadas
- [ ] Timer de grabación funciona
- [ ] Límite de 5 minutos funciona
- [ ] Permisos se solicitan correctamente
- [ ] Navegación sin crashes
- [ ] Notificaciones aparecen
- [ ] Contadores se actualizan
- [ ] Filtros funcionan correctamente

### Manejo de Errores
- [ ] Validación de campos vacíos
- [ ] Error de conexión manejado
- [ ] Permiso denegado manejado
- [ ] Timeout de subida manejado

### UI/UX
- [ ] Botones responden correctamente
- [ ] Animaciones suaves
- [ ] Cards diferenciados por tipo
- [ ] Colores y gradientes correctos
- [ ] Textos legibles
- [ ] Iconos apropiados

---

## 🎯 RESULTADOS ESPERADOS - RESUMEN

### Si TODO funciona correctamente:

✅ **Notas de Texto:**
- Se crean y guardan sin problemas
- Aparecen en HomeScreen y AllNotesScreen
- Se pueden eliminar
- Se pueden buscar

✅ **Notas de Voz:**
- Se graban correctamente
- Se suben a Firebase Storage
- Aparecen con icono de micrófono
- Muestran duración correcta
- Se pueden eliminar (audio + metadatos)

✅ **Interfaz:**
- Navegación fluida sin crashes
- Filtros funcionan
- Búsqueda funciona
- Notificaciones aparecen
- Contadores actualizados

✅ **Estabilidad:**
- No hay crashes
- No hay loops infinitos
- No hay memory leaks
- Sincronización con Firebase funciona

---

## 🆘 SI ALGO FALLA

### Crash al abrir HomeScreen con notas
**Causa probable:** Problema con LazyColumn
**Solución:** Ya debería estar resuelto con la eliminación de `heightIn`

### Notas no aparecen después de guardar
**Causa probable:** Usuario no autenticado o error de Firestore
**Verifica:**
```bash
adb logcat | Select-String "FirestoreService"
```

### Audio no se sube
**Causa probable:** Permisos o error de Storage
**Verifica:**
```bash
adb logcat | Select-String "StorageService"
```

### Crash al guardar
**Causa probable:** Error en ViewModel o Repository
**Verifica:**
```bash
adb logcat | Select-String "FATAL"
```

---

**¡Buena suerte con las pruebas! 🚀**

