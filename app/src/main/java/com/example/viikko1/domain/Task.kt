package com.example.viikko1.domain

import java.time.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: LocalDate,
    val done: Boolean
)

val mockTasks: List<Task> = listOf(
    Task(1, "Osta maitoa", "Käy kaupassa", 2, LocalDate.now().plusDays(1), false),
    Task(2, "Kävely", "30 min kävely", 1, LocalDate.now().plusDays(2), true),
    Task(3, "Siivoa", "Imuroi ja pyyhi pölyt", 3, LocalDate.now().plusDays(3), false),
    Task(4, "Koodaa", "Tee Compose-näkymä", 3, LocalDate.now().plusDays(4), false),
    Task(5, "Pese pyykit", "Valkoinen + värillinen", 2, LocalDate.now().plusDays(5), true),
    Task(6, "Lue", "Lue 20 sivua", 1, LocalDate.now().plusDays(6), false),
    Task(7, "Palauta tehtävä", "Vie repo", 3, LocalDate.now().plusDays(7), false)
)
