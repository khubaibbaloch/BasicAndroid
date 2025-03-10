package com.BasicAndroid.basicandroid.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.BasicAndroid.basicandroid.pagination.GitHubUser
import com.BasicAndroid.basicandroid.pagination.GitHubViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubUserScreen(viewModel: GitHubViewModel) {
    val users = viewModel.users.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("GitHub Users") }) }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            LazyColumn {
                items(users.itemCount) { index ->
                    Log.d("GitHubUser", "GitHubUserScreen: ${users[index]?.let { it }}")
                    users[index]?.let { GitHubUserItem(it) }
                }


                users.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> item { LoadingItem() }
                        loadState.append is LoadState.Loading -> item { LoadingItem() }
                        loadState.append is LoadState.Error -> item { ErrorItem { retry() } }
                    }
                }
            }
        }
    }
}

@Composable
fun GitHubUserItem(user: GitHubUser) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter: Painter = rememberImagePainter(data = user.avatarUrl)

        Image(
            painter = painter,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = user.login, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
}

@Composable
fun ErrorItem(retry: () -> Unit) {
    Button(onClick = retry, modifier = Modifier.padding(16.dp)) {
        Text("Retry")
    }
}
