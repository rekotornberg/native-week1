package com.example.viikko1.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task

@Composable
fun DetailScreen(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Int) -> Unit
) {
    var title by remember(task.id) { mutableStateOf(task.title) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tehtävän tiedot") },
        text = {
            androidx.compose.foundation.layout.Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Otsikko") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = {
                    onDelete(task.id)
                    onDismiss()
                }) {
                    Text("Poista tehtävä")
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val trimmed = title.trim()
                if (trimmed.isNotEmpty()) {
                    onSave(task.copy(title = trimmed))
                    onDismiss()
                }
            }) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Sulje")
            }
        }
    )
}
