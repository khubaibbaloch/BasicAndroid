package com.BasicAndroid.basicandroid.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences



// Define DataStore extension only ONCE in the entire project
val Context.dataStore by preferencesDataStore(name = "user_preferences")
