package com.reza.rahmani.pokes.ui.screens.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilImage
import com.reza.rahmani.pokes.R
import com.reza.rahmani.pokes.data.model.local.PokemonItem
import com.reza.rahmani.pokes.ui.Screen
import com.reza.rahmani.pokes.ui.theme.RobotoCondensed

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
    modifier: Modifier = Modifier, hint: String = "", onSearch: (String) -> Unit
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
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(MaterialTheme.colors.surface)
            .clickable {
                navController.navigate(Screen.PokemonDetailsScreen.createRoute(item.pokemonName))
            }) {
        Column {
            CoilImage(
                request = ImageRequest
                    .Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .build(),
                contentDescription = item.pokemonName,
                fadeIn = true,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            }
            Text(
                text = item.pokemonName,
                fontFamily = RobotoCondensed,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), hint = stringResource(
            id = R.string.hint_pokemon_list
        )
    ) {}
}