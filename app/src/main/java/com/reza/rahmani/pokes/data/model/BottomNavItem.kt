package com.reza.rahmani.pokes.data.model

import com.reza.rahmani.pokes.R

sealed class Screen(val route: String) {
    sealed class BottomNavItem(open val title: String, val icon: Int, val route: String) {
        data class Home(override val title: String) : BottomNavItem(title, R.drawable.ic_home, "home_screen") {
            companion object {
                fun route() = "home_screen"
            }
        }
        data class Settings(override val title: String) : BottomNavItem(title, R.drawable.ic_settings, "settings_screen") {
            companion object {
                fun route() = "settings_screen"
            }
        }
        data class Favorite(override val title: String) : BottomNavItem(title, R.drawable.ic_favorite, "favorite_screen") {
            companion object {
                fun route() = "favorite_screen"
            }
        }
    }

    object PokemonListScreen : Screen("pokemon_list_screen")
    object PokemonDetailsScreen : Screen("pokemon_details_screen/{dominantColor}/{pokemonName}") {
        fun createRoute(dominantColor: Int, pokemonName: String) =
            "pokemon_details_screen/$dominantColor/$pokemonName"
    }
}



