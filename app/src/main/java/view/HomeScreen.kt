package com.example.viikko1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel

private enum class TaskFilter { ALL, DONE, UNDONE }

@Composable
fun HomeScreen(vm: TaskViewModel) {

    var newTitle by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val tasks: List<Task> by vm.tasks.collectAsState()

    var filter by rememberSaveable { mutableStateOf(TaskFilter.ALL) }

    val filteredTasks: List<Task> = remember(tasks, filter) {
        when (filter) {
            TaskFilter.ALL -> tasks
            TaskFilter.DONE -> tasks.filter { it.done }
            TaskFilter.UNDONE -> tasks.filter { !it.done }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        TabRow(selectedTabIndex = filter.ordinal) {
            Tab(
                selected = filter == TaskFilter.ALL,
                onClick = { filter = TaskFilter.ALL },
                text = { Text("Kaikki") }
            )
            Tab(
                selected = filter == TaskFilter.DONE,
                onClick = { filter = TaskFilter.DONE },
                text = { Text("Tehdyt") }
            )
            Tab(
                selected = filter == TaskFilter.UNDONE,
                onClick = { filter = TaskFilter.UNDONE },
                text = { Text("Tekemättä") }
            )
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
                    vm.addTask(title)
                    newTitle = ""
                }
            }) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = filteredTasks,
                key = { task -> task.id }
            ) { task ->
                TaskRow(
                    title = task.title,
                    done = task.done,
                    onToggle = { vm.toggleDone(task.id) },
                    onRemove = { vm.removeTask(task.id) },
                    onOpen = { selectedTask = task }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    selectedTask?.let { task ->
        DetailScreen(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = { updated -> vm.updateTask(updated) },
            onDelete = { id -> vm.removeTask(id) }
        )
    }
}

@Composable
private fun TaskRow(
    title: String,
    done: Boolean,
    onToggle: () -> Unit,
    onRemove: () -> Unit,
    onOpen: () -> Unit
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
            Text(
                text = title,
                modifier = Modifier.clickable { onOpen() }
            )
        }

        Button(onClick = onRemove) {
            Text("Poista")
        }
    }
}
