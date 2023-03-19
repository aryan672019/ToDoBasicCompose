package com.letmelearn.todoapp.features.insertToDo.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letmelearn.todoapp.features.insertToDo.data.ToDoEntity
import com.letmelearn.todoapp.features.insertToDo.domain.GetToDoUseCase
import com.letmelearn.todoapp.features.insertToDo.domain.InsertToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val insertToDoUseCase: InsertToDoUseCase,
    private val getToDoUseCase: GetToDoUseCase
) : ViewModel() {


    val response : MutableState<List<ToDoEntity>> = mutableStateOf(listOf())

    init {
        getAllToDo()
    }

    fun insertEntry(toDoEntity: ToDoEntity) {
        viewModelScope.launch {
            insertToDoUseCase.insertToDoEntity(toDoEntity)
        }
    }

    private fun getAllToDo(){
        viewModelScope.launch {
            getToDoUseCase.getAllToDo().catch { e ->
                Log.d("Aryan", "getAllToDo() called ${e.message}")
            }.collect {
                response.value = it
            }
        }
    }
}