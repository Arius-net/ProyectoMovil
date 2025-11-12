package com.sayd.notaudio.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayd.notaudio.R
import com.sayd.notaudio.ui.theme.NotaudioTheme

@Composable
fun RegisterScreen() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Crea una cuenta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )
            Text(
                text = "Llene todos los campos",
                fontSize = 16.sp,
                color = Color.Gray
            )
//            Image(
//                painter = painterResource(id = R.drawable.placeholder),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(150.dp)
//                    .clip(RoundedCornerShape(16.dp)),
//                contentScale = ContentScale.Crop
//            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { /* TODO: Handle registration */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text(text = "Crear Cuenta", color = Color.White)
            }
            TextButton(onClick = { /* TODO: Navigate to login */ }) {
                Text(text = "Ya tengo cuenta")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    NotaudioTheme {
        RegisterScreen()
    }
}
