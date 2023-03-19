package com.letmelearn.todoapp.features.insertToDo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.letmelearn.todoapp.features.insertToDo.data.ToDoEntity
import com.letmelearn.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToDoActivity : ComponentActivity() {

private val toDoViewModel : ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GetToDoUi()
                }
            }
        }
    }



    @Composable
    fun GetToDoUi() {
        Log.d("Aryan", "GetToDoUi() called")
        Scaffold(topBar = {
                TopAppBar(
                    backgroundColor = Color.Black,
                    title = {
                        Text(text = "TO DO APP", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                )
            }, floatingActionButton = {
                val openDialog = remember {
                    mutableStateOf(false)
                }
                FloatingActionButton(
                    onClick = {
                    openDialog.value = true
                    }, backgroundColor = Color.Black
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Entry", tint = Color.White)
                    AddDialogBox(openDialog = openDialog)
                }
            }){
           AddRecycleView(toDoViewModel = toDoViewModel)
        }
    }


    @Composable
    fun AddDialogBox(openDialog: MutableState<Boolean>) {

        var alertDialogTitle = remember {
            mutableStateOf("")
        }

        var alertDialogDescription = remember {
            mutableStateOf("")
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "TO DO ", fontWeight = FontWeight.Bold)
                },
                text = {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = alertDialogTitle.value,
                            onValueChange = {
                                alertDialogTitle.value = it
                            },
                            label = {
                                Text(text = "Title")
                            },
                            placeholder = {
                                Text(text = "Enter title", modifier = Modifier.padding(3.dp))
                            },
                            modifier = Modifier.padding(10.dp)
                        )


                        OutlinedTextField(
                            value = alertDialogDescription.value,
                            onValueChange = {
                                alertDialogDescription.value = it
                            },
                            label = {
                                Text(text = "Description")
                            },
                            placeholder = {
                                Text(text = "Enter Description")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                },
                confirmButton = {
                    OutlinedButton(
                        onClick = {
                            val flag = insertData(alertDialogTitle.value,alertDialogDescription.value)
                            if(flag) openDialog.value = false
                        }
                    ) {
                        Text(text = "Save")
                    }
                },
            )
        }

    }

    private fun insertData(title:String, description:String):Boolean{
        return if(title.isNotEmpty() && description.isNotEmpty()){
            toDoViewModel.insertEntry(ToDoEntity(0,title,description))
            Toast.makeText(this,"Saved Successfully", Toast.LENGTH_SHORT).show()
             true
        }else{
            Toast.makeText(this,"Sorry Cannot Save Empty Data", Toast.LENGTH_SHORT).show()
             false
        }
    }

    @Composable
    fun createCardView(title:String, description: String){
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(CornerSize(5.dp))
        ) {
           Column(modifier = Modifier.padding(5.dp)) {
               Text(text = title, fontWeight = FontWeight.Bold)
               Text(text = description, fontWeight = FontWeight.Normal)
           }
        }
    }

    @Composable
    fun AddRecycleView(toDoViewModel: ToDoViewModel){
        Log.d("Aryan", "AddRecycleView() called with: toDoViewModel = $toDoViewModel")
        LazyColumn(){
            items(toDoViewModel.response.value){toDoEntity->
                 createCardView(title =toDoEntity.title , description = toDoEntity.description)
            }
        }
    }

}