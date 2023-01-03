package com.reza.rahmani.pokes.ui.screens.pokemonlist

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.reza.rahmani.pokes.R
import com.reza.rahmani.pokes.data.model.local.PokemonItem
import com.reza.rahmani.pokes.ui.Screen
import com.reza.rahmani.pokes.ui.theme.PokesTheme
import com.reza.rahmani.pokes.ui.theme.RobotoCondensed

@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Logo(
                contentDescription = R.string.title_pokemon_list,
                painterResource = R.drawable.ic_international_pok_mon_logo,
                modifier = Modifier.align(CenterHorizontally)
            )
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
        }
    }
}

@Composable
fun Logo(
    @StringRes contentDescription: Int,
    @DrawableRes painterResource: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = painterResource(id = painterResource),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        OutlinedTextField(
            value = text,
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                text = it
                onSearch(it)
            },
            label = {
                Text(text = stringResource(id = R.string.hint_pokemon_list))
            },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 2.dp,
                    bottom = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                )
        )
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    item: PokemonItem,
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .aspectRatio(1f)
            .background(MaterialTheme.colors.surface)
            .clickable {
                navController.navigate(Screen.PokemonDetailsScreen.createRoute(item.pokemonName))
            }
    ) {
        Column {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.pokemonName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
                    .clip(CircleShape)
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator(modifier = Modifier.scale(0.5f))
                } else {
                    SubcomposeAsyncImageContent()
                }
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

@Composable
fun Pokemons(
    list: List<PokemonItem>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) {
            PokemonItem(
                item = it, navController = navController
            )
        }
    }
}

@Preview(showBackground = true, name = "Logo")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Logo Dark")
@Composable
fun LogoPreview() {
    PokesTheme {
        Logo(
            contentDescription = R.string.title_pokemon_list,
            painterResource = R.drawable.ic_international_pok_mon_logo,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true, name = "SearchBar")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "SearchBar Dark")
@Composable
fun SearchBarPreview() {
    PokesTheme {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {}
    }
}

@Preview(showBackground = true, name = "PokemonItem")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PokemonItem Dark")
@Composable
fun PokemonItemPreview() {
    val navController = rememberNavController()
    PokesTheme {
        PokemonItem(
            item = PokemonItem(
                pokemonName = "Pokemon",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",
                number = 10
            ),
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}