package com.example.pc02moviles21100318_23200198

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pc02moviles21100318_23200198.ui.theme.PC02MOVILES2110031823200198Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC02MOVILES2110031823200198Theme {
                // Llama a la pantalla de registro directamente
                RegistroLiga1Screen()
            }
        }
    }
}


@Composable
fun RegistroLiga1Screen() {
    // Estados para almacenar los valores de los campos de texto
    var nombre by remember { mutableStateOf("") }
    var fundacion by remember { mutableStateOf("") }
    var titulos by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .imePadding() // Añadir espacio para el teclado virtual
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Registro Liga 1",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 2. Campos de Texto (se usa un componente reutilizable)
            CampoTextoRegistro(
                value = nombre,
                onValueChange = { nombre = it },
                label = "Nombre del equipo",
                keyboardType = KeyboardType.Text
            )
            CampoTextoRegistro(
                value = fundacion,
                onValueChange = { fundacion = it },
                label = "Año de fundación",
                keyboardType = KeyboardType.Number
            )
            CampoTextoRegistro(
                value = titulos,
                onValueChange = { titulos = it },
                label = "Número de títulos ganados",
                keyboardType = KeyboardType.Number
            )
            CampoTextoRegistro(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                label = "URL de la imagen del equipo",
                keyboardType = KeyboardType.Uri
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 3. Botón Guardar
            Button(
                onClick = {
                    if (nombre.isNotBlank() && fundacion.isNotBlank() && titulos.isNotBlank()) {
                        // Aquí iría la lógica real de Firebase Firestore (Parte 1 de la práctica)
                        Log.d("Registro", "Guardando Equipo: $nombre")
                        Toast.makeText(context, "Equipo '$nombre' guardado (Compose Simulación)", Toast.LENGTH_LONG).show()

                        // Limpiar campos después de guardar
                        nombre = ""
                        fundacion = ""
                        titulos = ""
                        imagenUrl = ""
                    } else {
                        Toast.makeText(context, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Guardar", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun CampoTextoRegistro(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun RegistroLiga1Preview() {
    PC02MOVILES2110031823200198Theme {
        RegistroLiga1Screen()
    }
}