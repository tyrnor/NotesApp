package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.usecases.DeleteTaskUseCase
import com.example.todoapp.domain.usecases.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            getAllTasksUseCase().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun setAllTaskHidden() {
        _tasks.value.forEach { task ->
            task.isIconsVisible = IsIconsVisible.Hidden
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val updatedList = _tasks.value.toMutableList()
            updatedList.remove(task)
            _tasks.value = updatedList
            deleteTaskUseCase(task)
        }
    }

    fun updateTaskIsIconsVisible(task: Task, isIconsVisible: IsIconsVisible) {
        task.isIconsVisible = isIconsVisible

        _tasks.value = _tasks.value.toMutableList().apply {
            val taskIndex = indexOfFirst { it.id == task.id }
            if (taskIndex != -1) {
                set(taskIndex, task)
            }
        }
    }
}