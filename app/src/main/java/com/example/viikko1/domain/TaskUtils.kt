package com.example.viikko1.domain

// lisää taskin
fun addTask(list: List<Task>, task: Task): List<Task> {
    return list + task
}
// vaihtaa done undoneksi ja toisinpäin
fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) task.copy(done = !task.done) else task
    }
}
// suodattaa vain valmiit taskit

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

// järjestä uusin ensin
fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}
