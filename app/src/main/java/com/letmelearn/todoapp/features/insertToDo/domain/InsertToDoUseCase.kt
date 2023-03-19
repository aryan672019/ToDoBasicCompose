package com.letmelearn.todoapp.features.insertToDo.domain

import com.letmelearn.todoapp.features.insertToDo.data.ToDoEntity
import com.letmelearn.todoapp.features.insertToDo.data.ToDoRepo
import javax.inject.Inject

class InsertToDoUseCase @Inject constructor(private val toDoRepo: ToDoRepo) {
    suspend fun insertToDoEntity(toDoEntity: ToDoEntity){
        toDoRepo.insertToDo(toDoEntity)
    }
}