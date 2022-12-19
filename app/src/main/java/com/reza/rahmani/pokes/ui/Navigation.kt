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
import androidx.navigation.navigation
import com.reza.rahmani.pokes.R
import com.reza.rahmani.pokes.ui.screens.favorite.FavoriteScreen
import com.reza.rahmani.pokes.ui.screens.pokemonlist.PokemonListScreen
import com.reza.rahmani.pokes.ui.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = Screen.BottomNavItem.Pokemons.route
    ) {
        composable(Screen.BottomNavItem.Setting.route) {
            SettingsScreen()
        }
        composable(Screen.BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(Screen.BottomNavItem.Pokemons.route) { backStackEntry ->
            PokemonListScreen(navController = navController)
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
        object Pokemons : BottomNavItem(R.string.item_pokemon_list, R.drawable.ic_home, "pokemon_list_screen")
        object Setting :
            BottomNavItem(R.string.item_setting, R.drawable.ic_settings, "settings_screen")

        object Favorite :
            BottomNavItem(R.string.item_favorite, R.drawable.ic_favorite, "favorite_screen")
    }

    object PokemonDetailsScreen : Screen("pokemon_details_screen/{pokemonName}") {
        fun createRoute(pokemonName: String) =
            "pokemon_details_screen/$pokemonName"
    }
}