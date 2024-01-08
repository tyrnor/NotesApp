package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.usecases.GetTaskByIdUseCase
import com.example.todoapp.domain.usecases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel(){
    private var _task = MutableStateFlow(
        Task(
            id = 0L,
            title = "",
            description = "",
            creationDate = null,
            isIconsVisible = IsIconsVisible.Hidden
        )
    )
    val task: StateFlow<Task> = _task

    fun getTask(id : Long) {
        viewModelScope.launch {
            getTaskByIdUseCase(id).collect{
                _task.value = it
            }
        }
    }
}