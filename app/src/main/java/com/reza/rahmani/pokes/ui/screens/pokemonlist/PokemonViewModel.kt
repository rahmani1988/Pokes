package com.reza.rahmani.pokes.ui.screens.pokemonlist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import com.reza.rahmani.pokes.data.repository.PokemonRepository
import com.reza.rahmani.pokes.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    private val _pokemons = mutableStateListOf<Pokemons>()
    val pokemons: List<Pokemons>
        get() = _pokemons

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch(handler) {
            val result = pokemonRepository.getPokemonsStream(20, 0)
            when(result) {

            }
        }
    }
}