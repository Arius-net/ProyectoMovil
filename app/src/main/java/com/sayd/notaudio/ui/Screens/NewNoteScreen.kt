package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayd.notaudio.utils.NotificationHelper
import com.sayd.notaudio.ui.theme.NotaudioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteTopAppBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))
            )
        )
    )
}

@Composable
fun NewTextNoteScreen(onBackClick: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }

    Scaffold(
        topBar = { NewNoteTopAppBar(title = "Nueva Nota de Texto", onBackClick = onBackClick) },
        containerColor = Color(0xFFF8F8F8)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Título *", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = title,
                        onValueChange = { if (it.length <= 100) title = it },
                        placeholder = { Text("Título de la nota...", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF8F8F8),
                            focusedContainerColor = Color(0xFFF8F8F8),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF8E2DE2)
                        )
                    )
                    Text(
                        text = "${title.length}/100",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        textAlign = TextAlign.End,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Contenido", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = content,
                        onValueChange = { if (it.length <= 5000) content = it },
                        placeholder = { Text("Escribe tus pensamientos, ideas o recordatorios", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF8F8F8),
                            focusedContainerColor = Color(0xFFF8F8F8),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF8E2DE2)
                        )
                    )
                    Text(
                        text = "${content.length}/5000",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        textAlign = TextAlign.End,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { 
                                notificationHelper.showNotification("Nota cancelada", "La nota de texto ha sido cancelada.")
                                onBackClick()
                             },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Text("Cancelar", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { 
                                notificationHelper.showNotification("Nota guardada", "La nota de texto ha sido guardada exitosamente.")
                                onBackClick()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF903EFF), Color(0xFF2B80FF))
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

                        ) {
                            Icon(Icons.Filled.Save, contentDescription = "Guardar", tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Guardar", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9E6)),
                border = BorderStroke(1.dp, Color(0xFFFFE0B2)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.Lightbulb,
                        contentDescription = "Consejo",
                        tint = Color(0xFFFFA000)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Consejo: Escribe libremente. Puedes editar esta nota en cualquier momento.",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun NewVoiceNoteScreen(onBackClick: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var isRecording by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }

    Scaffold(
        topBar = { NewNoteTopAppBar(title = "Nueva Nota de Voz", onBackClick = onBackClick) },
        containerColor = Color(0xFFF8F8F8)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Título *", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = { Text("Título de la grabación...", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF8F8F8),
                            focusedContainerColor = Color(0xFFF8F8F8),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF8E2DE2)
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp))
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color(0xFF2B80FF), RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Mic, contentDescription = "Grabar", tint = Color.White, modifier = Modifier.size(40.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (isRecording) "Grabando..." else "Presiona para grabar",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { isRecording = !isRecording },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B80FF))
                        ) {
                            Icon(Icons.Filled.Mic, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(if (isRecording) "Detener" else "Iniciar Grabación", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { 
                                notificationHelper.showNotification("Nota cancelada", "La nota de voz ha sido cancelada.")
                                onBackClick()
                             },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Text("Cancelar", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { 
                                notificationHelper.showNotification("Nota guardada", "La nota de voz ha sido guardada exitosamente.")
                                onBackClick()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            enabled = isRecording, 
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2B80FF).copy(alpha = 0.5f),
                                disabledContainerColor = Color(0xFF903EFF).copy(alpha = 0.4f)
                            )
                        ) {
                             Icon(Icons.Filled.Save, contentDescription = "Guardar", tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Guardar", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                border = BorderStroke(1.dp, Color(0xFFBBDEFB)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Mic, 
                        contentDescription = "Consejo",
                        tint = Color(0xFF2B80FF)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Conseiso: Asegúrate de estar en un lugar tranquilo para mejor calidad. Máximo 5 minutos.",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewTextNoteScreenPreview() {
    NotaudioTheme {
        NewTextNoteScreen(onBackClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NewVoiceNoteScreenPreview() {
    NotaudioTheme {
        NewVoiceNoteScreen(onBackClick = {})
    }
}
