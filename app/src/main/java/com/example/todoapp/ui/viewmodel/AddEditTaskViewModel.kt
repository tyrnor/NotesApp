package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.usecases.AddTaskUseCase
import com.example.todoapp.domain.usecases.GetAllTasksUseCase
import com.example.todoapp.domain.usecases.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: AddTaskUseCase,
) : ViewModel(){
    fun addTask(task: Task){
        viewModelScope.launch {
            addTaskUseCase(task)
        }
    }
}