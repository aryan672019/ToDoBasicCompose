package com.letmelearn.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.letmelearn.todoapp.features.insertToDo.ui.ToDoActivity
import com.letmelearn.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.qualifiers.ApplicationContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Button(onClick = {
            val intent = Intent( this, ToDoActivity::class.java)
            startActivity(intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Show List")
        }
    }
}


