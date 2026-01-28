package com.example.viikko1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko1.model.Task
import com.example.viikko1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow(mockTasks)
    val tasks: StateFlow<List<Task>> = _tasks

    private fun nextId(): Int =
        (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1

    fun addTask(title: String) {
        val trimmed = title.trim()
        if (trimmed.isEmpty()) return

        val newTask = Task(
            id = nextId(),
            title = trimmed,
            description = "",
            priority = 1,
            dueDate = LocalDate.now(),
            done = false
        )

        _tasks.update { current ->
            current + newTask
        }
    }

    fun toggleDone(id: Int) {
        _tasks.update { current ->
            current.map { task ->
                if (task.id == id) task.copy(done = !task.done) else task
            }
        }
    }

    fun removeTask(id: Int) {
        _tasks.update { current ->
            current.filterNot { it.id == id }
        }
    }

    fun updateTask(updated: Task) {
        _tasks.update { current ->
            current.map { task ->
                if (task.id == updated.id) updated else task
            }
        }
    }
}
