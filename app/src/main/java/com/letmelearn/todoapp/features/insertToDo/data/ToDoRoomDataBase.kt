package com.letmelearn.todoapp.features.insertToDo.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoRoomDataBase :RoomDatabase(){
    abstract fun getToDoDao():ToDoDao
}