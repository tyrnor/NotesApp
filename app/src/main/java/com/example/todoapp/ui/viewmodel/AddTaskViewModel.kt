package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.usecases.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel(){
    fun addTask(task: Task){
        viewModelScope.launch {
            addTaskUseCase(task)
        }
    }
}