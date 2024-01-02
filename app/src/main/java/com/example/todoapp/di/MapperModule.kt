package com.example.todoapp.di

import com.example.todoapp.domain.mappers.TaskMapper
import com.example.todoapp.domain.mappers.TaskMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideTaskMapper(): TaskMapper = TaskMapperImpl()

}