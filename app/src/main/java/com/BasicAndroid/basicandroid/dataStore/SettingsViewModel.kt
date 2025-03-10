package com.BasicAndroid.basicandroid.dataStore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    val darkMode: StateFlow<Boolean> = repository.darkMode.stateIn(
        viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.Lazily,
        initialValue = false
    )

    val language: StateFlow<String> = repository.language.stateIn(
        viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.Lazily,
        initialValue = "en"
    )

    val notifications: StateFlow<Boolean> = repository.notifications.stateIn(
        viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.Lazily,
        initialValue = true
    )

    // Toggle Dark Mode
    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDarkMode(enabled)
        }
    }

    // Change Language
    fun changeLanguage(language: String) {
        viewModelScope.launch {
            repository.setLanguage(language)
        }
    }

    // Enable/Disable Notifications
    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch {
            repository.setNotifications(enabled)
        }
    }
}
