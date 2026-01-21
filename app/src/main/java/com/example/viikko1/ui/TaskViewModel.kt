package com.example.viikko1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks
import java.time.LocalDate



class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var allTasks: List<Task> = emptyList()
    private var currentFilter: Boolean? = null

    init {
        allTasks = mockTasks
        tasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        applyFilter()
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
        applyFilter()
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filterNot { it.id == id }
        applyFilter()
    }

    fun filterByDone(done: Boolean) {
        currentFilter = done
        tasks = allTasks.filter { it.done == done }
    }

    fun showAll() {
        currentFilter = null
        tasks = allTasks
    }

    fun sortByDueDate() {
        allTasks = allTasks.sortedByDescending { it.dueDate }
        applyFilter()
    }

    fun nextId(): Int = (allTasks.maxOfOrNull { it.id } ?: 0) + 1

    fun nextDueDate(): LocalDate =
        (allTasks.maxOfOrNull { it.dueDate } ?: LocalDate.now()).plusDays(1)

    private fun applyFilter() {
        val f = currentFilter
        tasks = if (f == null) allTasks else allTasks.filter { it.done == f }
    }
}
