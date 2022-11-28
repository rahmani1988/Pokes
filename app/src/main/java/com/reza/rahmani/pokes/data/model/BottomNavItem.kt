package com.reza.rahmani.pokes.data.model

import androidx.annotation.StringRes
import com.reza.rahmani.pokes.R

sealed class Screen(val route: String) {
    sealed class BottomNavItem(@StringRes val titleResourceId: Int, val icon: Int, val route: String) {
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



