package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.domain.usecases.AddTaskUseCase
import com.example.todoapp.domain.usecases.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: AddTaskUseCase,
) : ViewModel(){
}