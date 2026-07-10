package com.example.basiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tanpa MaterialTheme wrapper, langsung panggil composable utama
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // Modifier dasar: padding, fillMaxSize
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(16.dp))
        CounterSection()
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldSection()
        Spacer(modifier = Modifier.height(16.dp))
        CheckboxSwitchSection()
        Spacer(modifier = Modifier.height(16.dp))
        CardSection()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Daftar Item (LazyColumn):",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ListSection()
    }
}

// 1. Text dasar + styling manual (tanpa Theme)
@Composable
fun HeaderSection() {
    Text(
        text = "Belajar Jetpack Compose Dasar",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray
    )
    Text(
        text = "Contoh ini tidak memakai file Theme.kt",
        fontSize = 14.sp,
        color = Color.Gray
    )
}

// 2. State: remember + mutableStateOf, Button, Row
@Composable
fun CounterSection() {
    // State lokal, akan re-compose saat berubah
    var counter by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Counter: $counter", fontSize = 18.sp)
        Row {
            Button(onClick = { counter-- }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { counter++ }) {
                Text("+")
            }
        }
    }
}
// 3. TextField dengan state
@Composable
fun TextFieldSection() {
    var name by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Masukkan nama") },
            modifier = Modifier.fillMaxWidth()
        )
        if (name.isNotEmpty()) {
            Text(text = "Halo, $name!", fontSize = 16.sp)
        }
    }
}
// 4. Checkbox & Switch
@Composable
fun CheckboxSwitchSection() {
    var isChecked by remember { mutableStateOf(false) }
    var isSwitchOn by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
        Text("Checkbox: $isChecked")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = isSwitchOn, onCheckedChange = { isSwitchOn = it })
        Text("Switch: $isSwitchOn")
    }
}
// 5. Card, Box, Modifier (border, background, clickable)
@Composable
fun CardSection() {
    var clicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clicked = !clicked },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (clicked) Color(0xFFDDEEFF) else Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = if (clicked) "Kartu diklik!" else "Klik kartu ini",
                fontSize = 16.sp
            )
        }
    }
}
// 6. LazyColumn (list scrollable)
@Composable
fun ListSection() {
    val items = List(20) { "Item ke-${it + 1}" }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        items(items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Divider()
        }
    }
}
