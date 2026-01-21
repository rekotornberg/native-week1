package com.example.viikko1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    var newTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Tehtävälista")

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { vm.showAll() }) { Text("All") }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vm.filterByDone(true) }) { Text("Done") }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vm.filterByDone(false) }) { Text("Undone") }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vm.sortByDueDate() }) { Text("Sort") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("Uusi tehtävä") },
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                val title = newTitle.trim()
                if (title.isNotEmpty()) {
                    vm.addTask(
                        Task(
                            id = vm.nextId(),
                            title = title,
                            description = "",
                            priority = 1,
                            dueDate = vm.nextDueDate(),
                            done = false
                        )
                    )
                    newTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = vm.tasks, key = { it.id }) { task ->
                TaskRow(
                    title = task.title,
                    done = task.done,
                    onToggle = { vm.toggleDone(task.id) },
                    onRemove = { vm.removeTask(task.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun TaskRow(
    title: String,
    done: Boolean,
    onToggle: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Checkbox(
                checked = done,
                onCheckedChange = { onToggle() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
        }

        Button(onClick = onRemove) {
            Text("Poista")
        }
    }
}
