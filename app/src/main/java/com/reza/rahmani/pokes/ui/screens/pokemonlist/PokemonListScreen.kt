package com.reza.rahmani.pokes.ui.screens.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.reza.rahmani.pokes.R
import com.reza.rahmani.pokes.data.model.local.PokemonItem


@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = stringResource(id = R.string.title_pokemon_list),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = stringResource(id = R.string.hint_pokemon_list)
            ) {

            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier, hint: String = "", onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(color = Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.hasFocus
                })
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    item: PokemonItem,
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel()
) {

}

@Preview
@Composable
fun Preview() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), hint = stringResource(
            id = R.string.hint_pokemon_list
        )
    )
}