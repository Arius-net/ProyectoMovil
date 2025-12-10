package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.theme.NotaudioTheme

@Composable
fun RemindersScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF0F0F0),
        bottomBar = { RemindersBottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Recordatorios",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A1B9A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Aquí verás tus recordatorios",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RemindersBottomNavigationBar(navController: NavController) {
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
fun RemindersScreenPreview() {
    NotaudioTheme {
        RemindersScreen(rememberNavController())
    }
}
