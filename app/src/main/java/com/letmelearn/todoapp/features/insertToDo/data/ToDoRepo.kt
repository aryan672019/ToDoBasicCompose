package com.letmelearn.todoapp.features.insertToDo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ToDoRepo @Inject constructor(private val toDoRoomDataBase: ToDoRoomDataBase) {

    suspend fun insertToDo(toDoEntity: ToDoEntity){
        withContext(Dispatchers.IO) {toDoRoomDataBase.getToDoDao().insertToDoEntry(toDoEntity)}
    }

    fun getToDo(): Flow<List<ToDoEntity>> {
        return toDoRoomDataBase.getToDoDao().getToDoEntries()
    }
}