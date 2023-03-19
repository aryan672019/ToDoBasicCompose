package com.letmelearn.todoapp.features.insertToDo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_table")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var description:String
)
