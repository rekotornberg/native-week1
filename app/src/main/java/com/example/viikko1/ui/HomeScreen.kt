package com.example.viikko1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.*

@Composable
fun HomeScreen() {

    var allTasks by remember { mutableStateOf(mockTasks) }
    var visibleTasks by remember { mutableStateOf(allTasks) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Tehtävälista",
            modifier = Modifier.padding(bottom = 8.dp)
        )


        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            Button(onClick = {
                val nextId = (allTasks.maxOfOrNull { it.id } ?: 0) + 1
                val newTask = Task(
                    id = nextId,
                    title = "Uusi tehtävä $nextId",
                    description = "Lisätty napista",
                    priority = 1,
                    dueDate = allTasks.last().dueDate.plusDays(1),
                    done = false
                )
                allTasks = addTask(allTasks, newTask)
                visibleTasks = allTasks
            }) {
                Text("Add")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                allTasks = sortByDueDate(allTasks)
                visibleTasks = allTasks
            }) {
                Text("Sort")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            Button(onClick = { visibleTasks = allTasks }) {
                Text("All")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { visibleTasks = filterByDone(allTasks, true) }) {
                Text("Done")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { visibleTasks = filterByDone(allTasks, false) }) {
                Text("Undone")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

            Column {
                visibleTasks.forEach { task ->
                    val statusText = if (task.done) "Done" else "Undone"
                    val statusIcon = if (task.done) "✓" else "▢"

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                allTasks = toggleDone(allTasks, task.id)
                                visibleTasks = allTasks
                            }
                            .padding(12.dp)
                    ) {
                        // Ylärivi: ikoni + otsikko
                        Row {
                            Text(text = "$statusIcon ")
                            Text(text = task.title)
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Alarivi: päivämäärä + tila
                        Text(
                            text = "${task.dueDate} • $statusText",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
    }
}
