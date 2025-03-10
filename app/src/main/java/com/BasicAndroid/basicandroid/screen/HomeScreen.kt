package com.BasicAndroid.basicandroid.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.BasicAndroid.basicandroid.DI.MyViewModel
import com.BasicAndroid.basicandroid.DI.Users
import com.BasicAndroid.basicandroid.RetrofitPractice.Contact
import com.BasicAndroid.basicandroid.RetrofitPractice.ContactViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    myViewModel: MyViewModel,
    contactViewModel: ContactViewModel,
) {
    val data by myViewModel.userData.collectAsState()
    val contacts by contactViewModel.contacts.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Title", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                // BottomAppBar content
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                items(data) { user ->
//                    UserItem(user = user)
//                }
//                item {
//                    Button(
//                        onClick = {
//                            val user = Users(id = 20, "parvez", age = 51)
//                            myViewModel.updateData(user)
//                        }
//                    ) {
//                        Text(text = "add Data")
//                    }
//                }
//            }
            val data = Contact(id = 3, name = "dallay", email = "ASDASDa", phone = "ASDAS")
            LazyColumn {
                items(contacts) { contact ->
                    ContactItem(
                        contact = contact,
                        onUpdateContact = { updatedContact -> contactViewModel.updateContact(contact.id, data) },
                        onDeleteContact = { contact.id.let {contactViewModel.deleteContact(it) } }
                    )
                }
                item {
                    Button(
                        onClick = {
                            val newContact = Contact(id = 3, name = "KB", email = "new@example.com", phone = "0987654321")
                            contactViewModel.addContact(newContact)
                        }
                    ) {
                        Text("Add Contact")
                    }
                }
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    onUpdateContact: (Contact) -> Unit,
    onDeleteContact: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = contact.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = contact.email, style = MaterialTheme.typography.bodyLarge)
            Text(text = contact.phone, style = MaterialTheme.typography.bodyLarge)
        }
        Row {
            IconButton(onClick = { onUpdateContact(contact.copy(name = "Updated Name")) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Contact")
            }
            IconButton(onClick = onDeleteContact) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Contact")
            }
        }
    }
}

@Composable
fun UserItem(user: Users) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "ID: ${user.id}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = "Name: ${user.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = "Age: ${user.age}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}


@Composable
fun DetailsScreen() {
    Text("Details Screen", fontSize = 24.sp)
}
