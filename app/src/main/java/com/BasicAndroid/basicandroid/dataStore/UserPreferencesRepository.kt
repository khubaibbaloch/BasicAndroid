package com.BasicAndroid.basicandroid.dataStore

import kotlinx.coroutines.flow.Flow

class UserPreferencesRepository(private val userPreferences: UserPreferences) {

    // Expose DataStore flows
    val darkMode: Flow<Boolean> = userPreferences.darkModeFlow
    val language: Flow<String> = userPreferences.languageFlow
    val notifications: Flow<Boolean> = userPreferences.notificationsFlow

    // Update Preferences
    suspend fun setDarkMode(enabled: Boolean) = userPreferences.saveDarkMode(enabled)
    suspend fun setLanguage(language: String) = userPreferences.saveLanguage(language)
    suspend fun setNotifications(enabled: Boolean) = userPreferences.saveNotifications(enabled)
}
