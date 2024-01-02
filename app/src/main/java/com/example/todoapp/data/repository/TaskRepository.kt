package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){
    val allTaks: Flow<List<TaskEntity>> = taskDao.getAllTasks()
    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }
    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
    fun getTaskById(id: Long): Flow<TaskEntity> {
        return taskDao.getTaskById(id)
    }
}