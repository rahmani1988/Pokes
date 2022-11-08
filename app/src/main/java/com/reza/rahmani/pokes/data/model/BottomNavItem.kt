package com.reza.rahmani.pokes.data.model

import com.reza.rahmani.pokes.R

sealed class Screen(val route: String) {
    sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
        object Home : BottomNavItem("Home", R.drawable.ic_home, "home_screen")
        object Settings : BottomNavItem("Settings", R.drawable.ic_settings, "settings_screen")
        object Favorite : BottomNavItem("Favorite", R.drawable.ic_favorite, "favorite_screen")
    }

    object PokemonListScreen : Screen("pokemon_list_screen")
    object PokemonDetailsScreen : Screen("pokemon_details_screen/{dominantColor}/{pokemonName}") {
        fun createRoute(dominantColor: Int, pokemonName: String) =
            "pokemon_details_screen/$dominantColor/$pokemonName"
    }
}

