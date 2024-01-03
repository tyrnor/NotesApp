package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.usecases.DeleteTaskUseCase
import com.example.todoapp.domain.usecases.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    val testTasks = MutableStateFlow(
        listOf(
            Task(
                id = 1,
                title = "Test Task 1",
                description = "Description 1",
                dueDate = null,
                isCompleted = false
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDate = null,
                isCompleted = true
            )

        )
    )

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
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val updatedList = testTasks.value.toMutableList()
            updatedList.remove(task)
            testTasks.value = updatedList
            deleteTaskUseCase(task)
        }
    }

}