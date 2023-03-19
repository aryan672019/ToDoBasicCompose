package com.letmelearn.todoapp.features.insertToDo.di

import android.content.Context
import androidx.room.Room
import com.letmelearn.todoapp.features.insertToDo.data.ToDoDao
import com.letmelearn.todoapp.features.insertToDo.data.ToDoRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun getDBInstance(@ApplicationContext context:Context):ToDoRoomDataBase{
       return Room.databaseBuilder(context, ToDoRoomDataBase::class.java,"ToDoTable").build()
    }

    @Provides
    @Singleton
    fun getDaoInstance(toDoRoomDataBase: ToDoRoomDataBase):ToDoDao{
        return toDoRoomDataBase.getToDoDao()
    }
}