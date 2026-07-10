package com.jaaDev.learn
import androidx.compose.ui.Alignment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaaDev.learn.ui.theme.learnTheme

// Data class buat 1 item todo
data class TodoItem(val text: String, var isDone: Boolean = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            learnTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TodoScreen()
                }
            }
        }
    }
}

@Composable
fun TodoScreen() {
    // State: list todo & text input
    val todoList = remember { mutableStateListOf<TodoItem>() }
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = "Todo List", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Input + tombol tambah
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Tugas baru") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    todoList.add(TodoItem(inputText))
                    inputText = ""
                }
            }) {
                Text("Tambah")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List todo
        LazyColumn {
            items(todoList) { todo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todo.isDone,
                        onCheckedChange = { todo.isDone = it }
                    )
                    Text(text = todo.text)
                }
            }
        }
    }
}