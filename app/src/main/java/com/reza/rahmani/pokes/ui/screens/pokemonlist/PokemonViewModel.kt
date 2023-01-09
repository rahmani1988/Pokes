package com.reza.rahmani.pokes.ui.screens.pokemonlist

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.rahmani.pokes.data.model.local.ResultWraper
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import com.reza.rahmani.pokes.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Result

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    private val _uiState: MutableStateFlow<List<Result?>?> = MutableStateFlow(null)
    val uiState = _uiState.asStateFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch(handler) {
            pokemonRepository.getPokemonsStream(20, 0)
                .collect {
                    when(it) {
                        is ResultWraper.Value -> {
                            _uiState.value = it.value?.results
                        }
                        is ResultWraper.Error -> {

                        }
                    }
                }
        }
    }
}

@Stable
interface PokemonsUiState {
    val errorMessage: String?
    val pokemons: List<Pokemons>?
}

private class MutablePokemonsUiState : PokemonsUiState {
    override var errorMessage: String? by mutableStateOf(null)
    override var pokemons: List<Pokemons>? by mutableStateOf(emptyList())
}
