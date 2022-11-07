package com.reza.rahmani.pokes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.reza.rahmani.pokes.data.model.BottomNavItem
import com.reza.rahmani.pokes.ui.screens.favorite.FavoriteScreen
import com.reza.rahmani.pokes.ui.screens.home.HomeScreen
import com.reza.rahmani.pokes.ui.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen()
        }
        composable(BottomNavItem.Favorite.screenRoute) {
            FavoriteScreen()
        }
    }
}