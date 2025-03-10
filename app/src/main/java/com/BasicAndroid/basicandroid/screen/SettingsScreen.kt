package com.BasicAndroid.basicandroid.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.BasicAndroid.basicandroid.dataStore.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val darkMode by viewModel.darkMode.collectAsState()
    val language by viewModel.language.collectAsState()
    val notifications by viewModel.notifications.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        // Toggle Dark Mode
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Dark Mode")
            Switch(checked = darkMode, onCheckedChange = { viewModel.toggleDarkMode(it) })
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Toggle Notifications
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Notifications")
            Switch(checked = notifications, onCheckedChange = { viewModel.toggleNotifications(it) })
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Language Selection
        Button(onClick = { viewModel.changeLanguage(if (language == "en") "es" else "en") }) {
            Text("Change Language (Current: $language)")
        }
    }
}
