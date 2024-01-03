package com.example.todoapp.domain.entities

import java.util.Date

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val dueDate: Date?,
    val isCompleted: Boolean,
    var isIconsVisible: Boolean = false
)