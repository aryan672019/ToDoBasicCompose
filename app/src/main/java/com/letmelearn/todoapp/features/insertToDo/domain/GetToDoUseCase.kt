package com.letmelearn.todoapp.features.insertToDo.domain

import com.letmelearn.todoapp.features.insertToDo.data.ToDoEntity
import com.letmelearn.todoapp.features.insertToDo.data.ToDoRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDoUseCase @Inject constructor(private val toDoRepo: ToDoRepo) {
    fun getAllToDo():Flow<List<ToDoEntity>>{
       return toDoRepo.getToDo()
    }
}