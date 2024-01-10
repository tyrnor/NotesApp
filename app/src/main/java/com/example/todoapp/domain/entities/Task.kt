package com.example.todoapp.domain.entities


import java.util.Date

data class Task(
    val id: Long,
    var title: String,
    var description: String,
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