package com.example.todoapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.Task

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.textViewTitle.text = task.title
    }
}

