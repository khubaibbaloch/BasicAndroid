package com.BasicAndroid.basicandroid.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.BasicAndroid.basicandroid.RoomDatabase.UserViewModel

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val user by viewModel.user.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.addUser(name, email)
            name = ""
            email = ""
        }) {
            Text("Add User")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.clearUsers() }) {
            Text("Clear Users")
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(user) { user ->
                Text(text = "${user.id}: ${user.name} - ${user.email}")
                Log.d(
                    "DataBase",
                    "UserScreen:${Text(text = "${user.id}: ${user.name} - ${user.email}")} "
                )
            }
        }
    }
}