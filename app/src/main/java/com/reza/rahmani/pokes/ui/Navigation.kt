package com.reza.rahmani.pokes.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.reza.rahmani.pokes.data.model.Screen
import com.reza.rahmani.pokes.ui.screens.favorite.FavoriteScreen
import com.reza.rahmani.pokes.ui.screens.home.HomeScreen
import com.reza.rahmani.pokes.ui.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = Screen.BottomNavItem.Home.route
    ) {
        composable(Screen.BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(Screen.BottomNavItem.Settings.route) {
            SettingsScreen()
        }
        composable(Screen.BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(Screen.PokemonListScreen.route) {

        }
        composable(
            Screen.PokemonDetailsScreen.route,
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
        }
    }
}