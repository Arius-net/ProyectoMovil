package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.theme.NotaudioTheme

enum class DescriptionType { Text, Audio }

@Composable
fun NewReminderScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var descriptionType by remember { mutableStateOf(DescriptionType.Text) }

    Scaffold(
        topBar = { NewReminderTopAppBar(navController) },
        bottomBar = { NewReminderBottomAppBar() },
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Título del recordatorio", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            placeholder = { Text("Ej: Reunión con el equipo") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = Color(0xFFF54EA2))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                DescriptionCard(descriptionType, onTypeChange = { descriptionType = it })
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                DateTimeCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                PriorityCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                RepeatCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                NotificationPreviewCard()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun NewReminderTopAppBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFFF54EA2), Color(0xFFFF7676)))
            )
            .padding(top=24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(text = "Nuevo Recordatorio", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DescriptionCard(type: DescriptionType, onTypeChange: (DescriptionType) -> Unit) {
    var text by remember { mutableStateOf("") }
    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Descripción (opcional)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row {
                    TextButton(onClick = { onTypeChange(DescriptionType.Text) }, shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.textButtonColors(containerColor = if(type == DescriptionType.Text) Color.Black else Color.LightGray, contentColor = if(type == DescriptionType.Text) Color.White else Color.Black)) {
                        Icon(Icons.Default.Description, contentDescription = "Text", modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Texto")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onTypeChange(DescriptionType.Audio) }, shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.textButtonColors(containerColor = if(type == DescriptionType.Audio) Color.Black else Color.LightGray, contentColor = if(type == DescriptionType.Audio) Color.White else Color.Black)) {
                        Icon(Icons.Default.Mic, contentDescription = "Audio", modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Audio")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (type == DescriptionType.Text) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Agrega detalles sobre este recordatorio...") },
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = Color(0xFFF54EA2))
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)).padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Mic, contentDescription = "Record", tint = Color.White, modifier = Modifier.size(40.dp).background(Color(0xFF2B80FF), CircleShape).padding(8.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Presiona para grabar", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B80FF))) {
                        Icon(Icons.Default.Mic, contentDescription = null, tint = Color.White)
                        Text("Iniciar Grabación", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DateTimeCard() {
    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Date and Time", modifier = Modifier.padding(end = 8.dp))
                Text("Fecha y Hora", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier.weight(1f).height(50.dp).background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)))
                Box(modifier = Modifier.weight(1f).height(50.dp).background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Recordatorio programado para: lunes, 3 de noviembre de 2025, 12:00", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp, top = 4.dp))
        }
    }
}

@Composable
fun PriorityCard() {
    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Bookmark, contentDescription = "Priority", modifier = Modifier.padding(end = 8.dp))
                Text("Prioridad", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)))
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFFFEED9), RoundedCornerShape(12.dp)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.RocketLaunch, contentDescription = null, tint = Color(0xFFFFA500), modifier = Modifier.size(20.dp))
                Text("Recordatorio de prioridad media", color = Color(0xFFFFA500), fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun RepeatCard() {
    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Repeat, contentDescription = "Repeat", modifier = Modifier.padding(end = 8.dp))
                Text("Repetir", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)))
        }
    }
}

@Composable
fun NotificationPreviewCard() {
    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color(0xFFF0F0F0))) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.Gray, modifier = Modifier.padding(end = 8.dp))
            Column {
                Text("Sin título", fontWeight = FontWeight.Bold)
                Text("3 nov", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun NewReminderBottomAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = Brush.horizontalGradient(listOf(Color(0xFFF54EA2), Color(0xFFFF7676))))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
                    Text("Guardar Recordatorio", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewReminderScreenPreview() {
    NotaudioTheme {
        NewReminderScreen(rememberNavController())
    }
}
