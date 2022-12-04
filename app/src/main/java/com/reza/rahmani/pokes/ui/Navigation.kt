package com.reza.rahmani.pokes.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.reza.rahmani.pokes.R
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

sealed class Screen(val route: String) {
    sealed class BottomNavItem(
        @StringRes val titleResourceId: Int,
        val icon: Int,
        val route: String
    ) {
        object Home : BottomNavItem(R.string.title_home, R.drawable.ic_home, "home_screen")
        object Settings :
            BottomNavItem(R.string.title_settings, R.drawable.ic_settings, "settings_screen")

        object Favorite :
            BottomNavItem(R.string.title_favorite, R.drawable.ic_favorite, "favorite_screen")
    }

    object PokemonListScreen : Screen("pokemon_list_screen")
    object PokemonDetailsScreen : Screen("pokemon_details_screen/{dominantColor}/{pokemonName}") {
        fun createRoute(dominantColor: Int, pokemonName: String) =
            "pokemon_details_screen/$dominantColor/$pokemonName"
    }
}