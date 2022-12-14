package com.reza.rahmani.pokes.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import com.reza.rahmani.pokes.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

}