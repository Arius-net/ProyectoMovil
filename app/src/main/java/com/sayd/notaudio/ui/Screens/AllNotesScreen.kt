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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayd.notaudio.data.model.Nota
import com.sayd.notaudio.ui.theme.NotaudioTheme
import com.sayd.notaudio.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AllNotesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToReminders: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToNewTextNote: () -> Unit,
    onNavigateToNewVoiceNote: () -> Unit
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val notes by homeViewModel.notes.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilterIndex by remember { mutableStateOf(0) }

    // Filter notes based on selected tab and search query
    val filteredNotes = remember(notes, selectedFilterIndex, searchQuery) {
        val baseFilter = when (selectedFilterIndex) {
            0 -> notes // All notes
            1 -> notes.filter { it.audioUrl.isEmpty() } // Text notes only
            2 -> notes.filter { it.audioUrl.isNotEmpty() } // Voice notes only
            else -> notes
        }

        if (searchQuery.isBlank()) {
            baseFilter
        } else {
            baseFilter.filter {
                (it.titulo?.contains(searchQuery, ignoreCase = true) == true) ||
                (it.descripcion?.contains(searchQuery, ignoreCase = true) == true)
            }
        }
    }

    val textNotesCount = notes.count { it.audioUrl.isEmpty() }
    val voiceNotesCount = notes.count { it.audioUrl.isNotEmpty() }

    Scaffold(
        containerColor = Color(0xFFF0F0F0),
        bottomBar = { AllNotesBottomNavigationBar(
            onNavigateToHome = onNavigateToHome,
            onNavigateToReminders = onNavigateToReminders,
            onNavigateToAllNotes = { /* Ya estamos aquí */ },
            onNavigateToSettings = onNavigateToSettings
        ) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Todas las Notas",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A1B9A)
                )
                Text(
                    text = "${notes.size} notas",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar notas...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Button(
                    onClick = onNavigateToNewTextNote,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
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
                            Icon(Icons.Default.Description, contentDescription = null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Nueva Nota de Texto", color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Button(
                    onClick = onNavigateToNewVoiceNote,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
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
                            Icon(Icons.Default.Mic, contentDescription = null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Nueva Nota de Voz", color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                NotesFilterTabs(
                    selectedIndex = selectedFilterIndex,
                    onTabSelected = { selectedFilterIndex = it },
                    totalCount = notes.size,
                    textCount = textNotesCount,
                    voiceCount = voiceNotesCount
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Show notes or empty state
            if (filteredNotes.isEmpty()) {
                item {
                    EmptyNotesView(
                        message = if (searchQuery.isNotEmpty())
                            "No se encontraron notas"
                        else
                            "No tienes notas"
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            } else {
                items(filteredNotes, key = { it.id }) { note ->
                    NoteCard(
                        note = note,
                        onDeleteNote = { homeViewModel.deleteNote(note) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: Nota, onDeleteNote: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = note.titulo ?: "Nota sin título",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (note.audioUrl.isNotEmpty()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Mic,
                                contentDescription = "Nota de voz",
                                tint = Color(0xFFE91E63),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Nota de voz • ${note.duracion}s",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Description,
                                contentDescription = "Nota de texto",
                                tint = Color(0xFF8E24AA),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Nota de texto",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        if (!note.descripcion.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = note.descripcion.take(100) + if (note.descripcion.length > 100) "..." else "",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = formatDate(note.fechaCreacion),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = onDeleteNote) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = Color(0xFFD32F2F)
                    )
                }
            }
        }
    }
}

fun formatDate(timestamp: Long): String {
    if (timestamp == 0L) return "Fecha desconocida"
    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("es", "ES"))
    return sdf.format(Date(timestamp))
}

@Composable
fun NotesFilterTabs(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    totalCount: Int,
    textCount: Int,
    voiceCount: Int
) {
    val tabs = listOf(
        "Todas ($totalCount)",
        "Texto ($textCount)",
        "Voz ($voiceCount)"
    )

    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.Transparent,
            contentColor = Color.DarkGray
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { onTabSelected(index) },
                    text = { Text(title) },
                    selectedContentColor = Color(0xFF6A1B9A),
                    unselectedContentColor = Color.Gray
                )
            }
        }
    }
}

@Composable
fun EmptyNotesView(message: String = "No tienes notas") {
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
            Icon(
                Icons.Default.MenuBook,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(message, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Crea tu primera nota usando los botones de arriba",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AllNotesBottomNavigationBar(
    onNavigateToHome: () -> Unit,
    onNavigateToReminders: () -> Unit,
    onNavigateToAllNotes: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(2) }
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
fun AllNotesScreenPreview() {
    NotaudioTheme {
        AllNotesScreen(
            onNavigateBack = {},
            onNavigateToHome = {},
            onNavigateToReminders = {},
            onNavigateToSettings = {},
            onNavigateToNewTextNote = {},
            onNavigateToNewVoiceNote = {}
        )
    }
}