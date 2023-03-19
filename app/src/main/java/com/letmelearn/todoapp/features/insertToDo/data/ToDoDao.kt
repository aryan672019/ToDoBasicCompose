package com.letmelearn.todoapp.features.insertToDo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDoEntry(toDoEntity: ToDoEntity)

    @Query("SELECT * FROM to_do_table")
    fun getToDoEntries(): Flow<List<ToDoEntity>>
}