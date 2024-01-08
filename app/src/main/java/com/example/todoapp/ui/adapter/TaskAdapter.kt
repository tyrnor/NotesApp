package com.example.todoapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task

class TaskAdapter : ListAdapter<Task, TaskViewHolder>(TaskDiffCallback()) {

    interface TaskActions {
        fun onDeleteTask(task: Task)
    }

    interface UpdateIconVisibility{
        fun updateIconVisibility (task: Task, isIconsVisible: IsIconsVisible)
    }

    var taskActions: TaskActions? = null
    var updateIconVisibility: UpdateIconVisibility? = null
    private var expandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, taskActions, updateIconVisibility, position == expandedPosition)  { newPosition ->
            Log.i("TAG", "onBindViewHolder: New position $newPosition and expandedPosition $expandedPosition")
            if (newPosition != expandedPosition){
                val prevPosition = expandedPosition
                if (prevPosition != -1 && task.isIconsVisible == IsIconsVisible.Visible){
                    updateIconVisibility?.updateIconVisibility(getItem(prevPosition), isIconsVisible = IsIconsVisible.PrevIcon)
                }
                expandedPosition = newPosition
            }
            notifyDataSetChanged()
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
