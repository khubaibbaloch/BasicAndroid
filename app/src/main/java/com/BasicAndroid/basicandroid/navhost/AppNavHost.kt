package com.BasicAndroid.basicandroid.navhost

import com.BasicAndroid.basicandroid.WebSocket.BinanceViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.BasicAndroid.basicandroid.DI.MyViewModel
import com.BasicAndroid.basicandroid.screen.DetailsScreen
import com.BasicAndroid.basicandroid.screen.HomeScreen
import com.BasicAndroid.basicandroid.RetrofitPractice.ContactViewModel
import com.BasicAndroid.basicandroid.RoomDatabase.UserViewModel
import com.BasicAndroid.basicandroid.dataStore.SettingsViewModel
import com.BasicAndroid.basicandroid.pagination.GitHubViewModel
import com.BasicAndroid.basicandroid.screen.BinanceTickerScreen
import com.BasicAndroid.basicandroid.screen.GitHubUserScreen
import com.BasicAndroid.basicandroid.screen.SettingsScreen
import com.BasicAndroid.basicandroid.screen.UserScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    myViewModel: MyViewModel,
    contactViewModel: ContactViewModel,
    binanceViewModel: BinanceViewModel,
    gitHubViewModel: GitHubViewModel,
    userViewModel: UserViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(navController = navController, startDestination = "SettingsScreen") {
        composable("home") { HomeScreen(navController, myViewModel, contactViewModel) }
        composable("details") { DetailsScreen() }
        composable("BinanceTickerScreen") { BinanceTickerScreen(binanceViewModel) }
        composable("GitHubUserScreen") { GitHubUserScreen(gitHubViewModel) }
        composable("UserScreen") {UserScreen(userViewModel)}
        composable("SettingsScreen") { SettingsScreen(settingsViewModel) }
    }
}
