package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayd.notaudio.ui.theme.NotaudioTheme
import com.sayd.notaudio.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToReminders: () -> Unit,
    onNavigateToAllNotes: () -> Unit
) {
    val authViewModel: AuthViewModel = koinViewModel()

    Scaffold(
        containerColor = Color(0xFFF0F0F0),
        bottomBar = { SettingsBottomNavigationBar(
            onNavigateToHome = onNavigateToHome,
            onNavigateToReminders = onNavigateToReminders,
            onNavigateToAllNotes = onNavigateToAllNotes,
            onNavigateToSettings = { /* Ya estamos aquí */ }
        ) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Configuración",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A1B9A)
                )
                Text(
                    text = "Personaliza tu experiencia",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            item { UserProfileCard(onLogout = onLogout, authViewModel = authViewModel) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PreferencesCard() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { InformationCard() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { DangerZoneCard(authViewModel, onLogout) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun UserProfileCard(onLogout: () -> Unit, authViewModel: AuthViewModel) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF6200EE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("U", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Usuario", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Sin correo", fontSize = 14.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Tu nombre") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("tu@email.com") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    // Lógica para guardar cambios de perfil
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF8E24AA), Color(0xFF42A5F5))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Guardar Cambios", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun PreferencesCard() {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Preferencias", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            PreferenceItem(
                icon = Icons.Outlined.Notifications,
                title = "Notificaciones",
                subtitle = "Recordatorios y alertas"
            ) {
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF6A1B9A)
                    )
                )
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            PreferenceItem(
                icon = Icons.Outlined.NightsStay,
                title = "Modo oscuro",
                subtitle = "Próximamente"
            ) {
                Switch(
                    checked = false,
                    onCheckedChange = { },
                    enabled = false
                )
            }
        }
    }
}

@Composable
fun InformationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to About screen */ },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Info,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Acerca de", fontWeight = FontWeight.Medium)
                Text("Versión 1.0.0", color = Color.Gray, fontSize = 12.sp)
            }
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun DangerZoneCard(authViewModel: AuthViewModel, onLogout: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0)),
        border = BorderStroke(1.dp, Color(0xFFFFCDCD))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Zona de Peligro", color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = {
                    authViewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(1.dp, Color.Red)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Eliminar todas las notas y cerrar sesión", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Esta acción es irreversible y cierra tu sesión",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PreferenceItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF3E5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFF6A1B9A))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium)
            Text(subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        content()
    }
}

@Composable
fun SettingsBottomNavigationBar(
    onNavigateToHome: () -> Unit,
    onNavigateToReminders: () -> Unit,
    onNavigateToAllNotes: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(3) }
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
fun SettingsScreenPreview() {
    NotaudioTheme {
        SettingsScreen(
            onNavigateBack = {},
            onLogout = {},
            onNavigateToHome = {},
            onNavigateToReminders = {},
            onNavigateToAllNotes = {}
        )
    }
}
