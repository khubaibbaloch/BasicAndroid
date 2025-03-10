package com.BasicAndroid.basicandroid

import com.BasicAndroid.basicandroid.WebSocket.BinanceViewModel
import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.BasicAndroid.basicandroid.ContentProvider.Photos
import com.BasicAndroid.basicandroid.ContentProvider.photoProvider
import com.BasicAndroid.basicandroid.ui.theme.BasicAndroidTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.BasicAndroid.basicandroid.DI.MyViewModel
import com.BasicAndroid.basicandroid.RetrofitPractice.ContactViewModel
import com.BasicAndroid.basicandroid.RoomDatabase.UserDatabase
import com.BasicAndroid.basicandroid.RoomDatabase.UserRepository
import com.BasicAndroid.basicandroid.RoomDatabase.UserViewModel
import com.BasicAndroid.basicandroid.dataStore.SettingsViewModel
import com.BasicAndroid.basicandroid.dataStore.UserPreferences
import com.BasicAndroid.basicandroid.dataStore.UserPreferencesRepository
import com.BasicAndroid.basicandroid.navhost.AppNavHost
import com.BasicAndroid.basicandroid.pagination.GitHubViewModel
import com.BasicAndroid.basicandroid.practiceClasses.BasicClasses
import com.BasicAndroid.basicandroid.practiceClasses.Collections
import com.BasicAndroid.basicandroid.practiceClasses.Coroutines
import com.BasicAndroid.basicandroid.practiceClasses.NullSafety
import com.BasicAndroid.basicandroid.practiceClasses.PracticeViewModel
import com.BasicAndroid.basicandroid.practiceClasses.myThread
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Existing properties
    val basicClasses = BasicClasses()
    val basicCollections = Collections()
    val basicNullSafety = NullSafety()
    val thread = myThread()
    val photoProvider = photoProvider(this)

    val requestMultiplePermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach { permission ->
            when (permission.key) {
                Manifest.permission.POST_NOTIFICATIONS -> {
                    if (permission.value) {
                        Log.d("Permissions", "Permissions:${permission.key},${permission.value} ")
                    } else {
                        Log.d("Permissions", "Permissions:${permission.key},${permission.value} ")
                    }
                }

                Manifest.permission.READ_SMS -> {
                    if (permission.value) {
                        Log.d("Permissions", "Permissions:${permission.key},${permission.value} ")
                    } else {
                        Log.d("Permissions", "Permissions:${permission.key},${permission.value} ")
                    }
                }
            }
        }
    }


    private val mainHandler = Handler(Looper.getMainLooper())
    private var allPhotos = listOf<Photos>()
    private var allFolders = listOf<String>()
    private var updateUi: Boolean = false

    // viewModel
    private val practiceViewModel: PracticeViewModel by viewModels()
    val coroutines = Coroutines()

    private val myViewModel: MyViewModel by viewModels()

    private val contactViewModel: ContactViewModel by viewModels()

    private val binanceViewModel: BinanceViewModel by viewModels()

    private val gitHubViewModel : GitHubViewModel by viewModels()

    private lateinit var userViewModel: UserViewModel
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize database and ViewModel inside onCreate
        val database = UserDatabase.getDataBase(applicationContext)
        val repository = UserRepository(database.userDao())
        userViewModel = UserViewModel(repository)

        // Initialize datastore and ViewModel inside onCreate
        val userPreferences = UserPreferences(this)
        val userPreferencesRepository =UserPreferencesRepository(userPreferences)
        settingsViewModel = SettingsViewModel(userPreferencesRepository)



        enableEdgeToEdge()
        setContent {
            BasicAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    thread.start()
//
//                    val runnable = Runnable {
//                        basicClasses.dataClass()
//                        basicClasses.sealedPractice(LoginResult.Success(34223423))
//
//                        basicCollections.mapPractice()
//                        basicCollections.filterPractice()
//                        basicCollections.reducePractice()
//                        basicCollections.zipPractice()
//                        basicCollections.groupByPractice()
//
//                        basicNullSafety.safeCall()
//                        basicNullSafety.Run()
//                        basicNullSafety.Apply()
//                        basicNullSafety.Also()
//                    }
//                    val myThread = Thread(runnable)
//                    myThread.start()
//
//                    // Start services
//                    startService(Intent(this, PracticeForegroundService::class.java))
//
//                    Thread{
//                        allPhotos = photoProvider.fetchPhoto()
//                        allFolders = photoProvider.folderNames()
//                        Log.d("Thread", "Thread:${Thread.currentThread()} ")
//                        mainHandler.post{
//                            Log.d("Thread", "mainHandleThread:${Thread.currentThread()} ")
//                            updateUi = true
//                        }
//                    }.start()

//                        Greeting(
//                            modifier = Modifier.padding(innerPadding),
//                            photoList = allPhotos,
//                            folderName = allFolders
//                        )
//                    }

                    // UI Components
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Button(
//                            onClick = {
//                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                                startActivity(intent)
//                            }
//                        ) {
//                            Text("Call me")
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Button(
//                            onClick = {
//                                requestMultiplePermissions()
//                            }
//                        ) {
//                            Text("Request Notification Permission")
//                        }
//                    }


                    // lazyColumn
//                    val listState = rememberLazyListState() // Keeps track of scroll position
//
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(Color.LightGray),
//                        state = listState, // Attach scroll state
//                        contentPadding = PaddingValues(16.dp), // Add padding around list
//                        reverseLayout = false, // Normal order (true makes it start from bottom)
//                        verticalArrangement = Arrangement.spacedBy(10.dp), // Space between items
//                        horizontalAlignment = Alignment.CenterHorizontally, // Align items to center
//                        flingBehavior = ScrollableDefaults.flingBehavior(), // Default fling behavior
//                        userScrollEnabled = true // Enable manual scrolling
//                    ) {
//                        items(10) { index ->
//                            Text(
//                                text = "Item $index",
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp)
//                                    .background(Color.White),
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }


                    // coroutines.runBlockingAsyncSimple()
//                    val data: Flow<Int> = flow {
//                        for (i in 1..300) {
//                            delay(1000)
//                            emit(i)
//                        }
//                    }
//                    lifecycleScope.launch{
//                        data.collect { value ->
//                            Log.d("Flow", "Collected value: $value")
//                        }
//                    }
                    val navController = rememberNavController()
                    AppNavHost(
                        navController,
                        myViewModel = myViewModel,
                        contactViewModel,
                        binanceViewModel,
                        gitHubViewModel,
                        userViewModel,
                        settingsViewModel
                    )
                }
            }
        }
    }


    private fun requestMultiplePermissions() {
        val permissionRequest = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("Permissions", "Permissions: Granted")

            } else {
                permissionRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            Log.d("Permissions", "Permissions: No need")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("Permissions", "Permissions: Granted")

            } else {
                permissionRequest.add(Manifest.permission.READ_SMS)
            }
        } else {
            Log.d("Permissions", "Permissions: No need")
        }

        if (permissionRequest.isNotEmpty()) {
            showPermissionRationaleDialog(permissionRequest.toTypedArray())
        }
    }

    private fun showPermissionRationaleDialog(permissions: Array<String>) {
        AlertDialog.Builder(this)
            .setTitle("Permissions Needed")
            .setMessage("This app requires notification and SMS permissions to function properly.")
            .setPositiveButton("Allow") { dialog, _ ->
                // Request permissions after explanation
                requestMultiplePermissions.launch(permissions)
                dialog.dismiss()
            }
            .setNegativeButton("Deny") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}

@Composable
fun Greeting(modifier: Modifier = Modifier, photoList: List<Photos>, folderName: List<String>) {
    var selectedFolder by rememberSaveable { mutableStateOf<String?>(null) }

    val groupedPhotos = photoList.groupBy { it.folderName }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(folderName) { folder ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .clickable {
                        selectedFolder = if (selectedFolder == folder) null else folder
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = folder,
                    fontSize = 22.sp,
                    color = Color.White
                )

                if (selectedFolder == folder) {
                    val photos = groupedPhotos[folder] ?: emptyList()
                    photos.forEach { photo ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            AsyncImage(
                                model = photo.image,
                                contentDescription = "Image",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(
                                text = photo.tittle,
                                fontSize = 16.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }


    }
}

