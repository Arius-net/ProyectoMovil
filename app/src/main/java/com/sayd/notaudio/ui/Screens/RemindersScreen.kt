package com.sayd.notaudio.ui.Screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.theme.NotaudioTheme

@Composable
fun RemindersScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAllNotes: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToNewReminder: () -> Unit
) {
    Scaffold(
        topBar = { RemindersTopAppBar() },
        bottomBar = { RemindersBottomNavigationBar(
            onNavigateToHome = onNavigateToHome,
            onNavigateToReminders = { /* Ya estamos aquí */ },
            onNavigateToAllNotes = onNavigateToAllNotes,
            onNavigateToSettings = onNavigateToSettings
        ) },
        containerColor = Color(0xFFF0F0F0)
    ) { innerPadding ->
        var searchQuery by remember { mutableStateOf("") }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar recordatorios...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Button(
                    onClick = onNavigateToNewReminder,
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
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                            Text("Crear Nuevo Recordatorio", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Próximos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "0 recordatorios", fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                EmptyRemindersView()
            }
        }
    }
}

@Composable
fun RemindersTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFFF54EA2), Color(0xFFFF7676)))
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Reminders Icon", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Recordatorios",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Organiza tus tareas pendientes",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun EmptyRemindersView() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 48.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFF0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.CalendarToday,
                    contentDescription = null,
                    tint = Color(0xFFFFA000),
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("No tienes recordatorios pendientes", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Crea una nota y márcala como recordatorio",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RemindersBottomNavigationBar(
    onNavigateToHome: () -> Unit,
    onNavigateToReminders: () -> Unit,
    onNavigateToAllNotes: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(1) }
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Inicio") },
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                onNavigateToHome()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6A1B9A),
                selectedTextColor = Color(0xFF6A1B9A),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Notifications, contentDescription = null) },
            label = { Text("Recordatorios") },
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onNavigateToReminders()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6A1B9A),
                selectedTextColor = Color(0xFF6A1B9A),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Description, contentDescription = null) },
            label = { Text("Notas") },
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                onNavigateToAllNotes()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6A1B9A),
                selectedTextColor = Color(0xFF6A1B9A),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            label = { Text("Perfil") },
            selected = selectedIndex == 3,
            onClick = {
                selectedIndex = 3
                onNavigateToSettings()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6A1B9A),
                selectedTextColor = Color(0xFF6A1B9A),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RemindersScreenPreview() {
    NotaudioTheme {
        RemindersScreen(onNavigateBack = {}, onNavigateToHome = {}, onNavigateToAllNotes = {}, onNavigateToSettings = {}, onNavigateToNewReminder = {})
    }
}
