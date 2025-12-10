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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.theme.NotaudioTheme

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF0F0F0),
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Mi Diario",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A1B9A)
                )
                Text(
                    text = "Bienvenido de nuevo",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                QuoteOfTheDayCard()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ActionButtons(navController)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SearchBar()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                MyNotesSection()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun QuoteOfTheDayCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.padding(end = 12.dp)
            )
            Column {
                Text(
                    text = "Frase del día",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "La escritura es la pintura de la voz.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ActionButtons(navController: NavController) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(
            onClick = { navController.navigate("new_text_note") },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = Brush.horizontalGradient(listOf(Color(0xFF8E24AA), Color(0xFF42A5F5))))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Edit, contentDescription = null, tint=Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Nueva Nota de Texto", color = Color.White)
                }
            }
        }
        Button(
            onClick = { navController.navigate("new_voice_note") },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = Brush.horizontalGradient(listOf(Color(0xFFE91E63), Color(0xFFFF9800))))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Mic, contentDescription = null, tint=Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Nueva Nota de Voz", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Buscar notas...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        shape = RoundedCornerShape(50.dp)
    )
}

@Composable
fun MyNotesSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mis Notas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "0 notas", fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Aún no tienes notas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Comienza creando tu primera nota",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
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
                navController.navigate("home") 
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
                navController.navigate("reminders")
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
                navController.navigate("all_notes")
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
                navController.navigate("settings")
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
fun HomeScreenPreview() {
    NotaudioTheme {
        HomeScreen(rememberNavController())
    }
}
