package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.sayd.notaudio.ui.theme.NotaudioTheme
import com.sayd.notaudio.utils.NotificationHelper

@Composable
fun NewTextNoteScreen(onBackClick: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }

    Scaffold(
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                    Text(
                        text = "Nueva Nota de Texto",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A1B9A)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { if (it.length <= 100) title = it },
                            label = { Text("Título de la nota...") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Text(
                            text = "${title.length}/100",
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, end = 8.dp),
                            textAlign = TextAlign.End, fontSize = 12.sp, color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = content,
                            onValueChange = { if (it.length <= 5000) content = it },
                            label = { Text("Escribe tus pensamientos...") },
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Text(
                            text = "${content.length}/5000",
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, end = 8.dp),
                            textAlign = TextAlign.End, fontSize = 12.sp, color = Color.Gray
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
                                modifier = Modifier.weight(1f).height(50.dp),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(1.dp, Color.Gray)
                            ) {
                                Text("Cancelar", fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = {
                                    notificationHelper.showNotification("Nota guardada", "La nota de texto ha sido guardada exitosamente.")
                                    onBackClick()
                                },
                                modifier = Modifier.weight(1f).height(50.dp),
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush = Brush.horizontalGradient(listOf(Color(0xFF8E24AA), Color(0xFF42A5F5)))),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Filled.Save, contentDescription = "Guardar", tint=Color.White)
                                        Spacer(Modifier.width(8.dp))
                                        Text("Guardar", color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9E6)),
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
                Spacer(modifier = Modifier.height(24.dp))
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
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                    Text(
                        text = "Nueva Nota de Voz",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A1B9A)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Título de la grabación...") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF0F0F0), RoundedCornerShape(20.dp))
                                .padding(vertical = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(Color(0xFF8E24AA), CircleShape),
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
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.height(50.dp).padding(horizontal=32.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush = Brush.horizontalGradient(listOf(Color(0xFF8E24AA), Color(0xFF42A5F5)))),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(if (isRecording) "Detener" else "Iniciar Grabación", color = Color.White, fontWeight = FontWeight.Bold)
                                }
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
                                modifier = Modifier.weight(1f).height(50.dp),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(1.dp, Color.Gray)
                            ) {
                                Text("Cancelar", fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = {
                                    notificationHelper.showNotification("Nota guardada", "La nota de voz ha sido guardada exitosamente.")
                                    onBackClick()
                                },
                                modifier = Modifier.weight(1f).height(50.dp),
                                enabled = isRecording,
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            brush = Brush.horizontalGradient(listOf(Color(0xFF8E24AA), Color(0xFF42A5F5))),
                                            alpha = if (isRecording) 1f else 0.4f
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Filled.Save, contentDescription = "Guardar", tint = Color.White)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Guardar", color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Mic,
                            contentDescription = "Consejo",
                            tint = Color(0xFF42A5F5)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            "Consejo: Asegúrate de estar en un lugar tranquilo para mejor calidad. Máximo 5 minutos.",
                            fontSize = 14.sp,
                            color = Color.DarkGray,
                            lineHeight = 20.sp
                        )
                    }
                }
                 Spacer(modifier = Modifier.height(24.dp))
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
