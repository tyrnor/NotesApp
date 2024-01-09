package com.example.todoapp.domain.entities


import java.util.Date

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val creationDate: Date?,
    var isIconsVisible: IsIconsVisible? = IsIconsVisible.Hidden
) {
    constructor(
        title: String,
        description: String,
        creationDate: Date?,
        isIconsVisible: IsIconsVisible
    ) : this(0L, title, description, creationDate, isIconsVisible)
}

enum class IsIconsVisible {
    Hidden,
    Visible,
    PrevIcon
}