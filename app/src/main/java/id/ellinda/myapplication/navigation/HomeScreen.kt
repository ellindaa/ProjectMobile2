package id.ellinda.myapplication.navigation

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import id.ellinda.myapplication.models.Data
import id.ellinda.myapplication.viewModel.MovieViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val movieViewModel = viewModel<MovieViewModel>()
    val state by movieViewModel.state.collectAsState()

    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }

    // Menggunakan LazyGridState yang sesuai untuk LazyVerticalGrid
    val gridState = rememberLazyGridState()

    // Menggunakan LaunchedEffect untuk mendeteksi ketika scroll mencapai bawah
    LaunchedEffect(gridState.firstVisibleItemIndex) {
        if (gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == state.movies.size - 1) {
            movieViewModel.loadNextItems()
        }
    }

    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar(searchQuery) { query ->
                searchQuery = query
                movieViewModel.searchMovies(query)
            }
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {

                val filteredMovies = state.movies.filter { movie ->
                    searchQuery.isEmpty() || movie.title.contains(searchQuery, ignoreCase = true)
                }

                items(filteredMovies.size) { index ->
                    ItemUi(
                        itemIndex = index,
                        movieList = filteredMovies,
                        navController = navController
                    )
                }

                // Display loading and error messages
                if (state.isLoading) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (!state.error.isNullOrEmpty()) {
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }

        },
        containerColor = Color.Transparent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUi(itemIndex: Int, movieList: List<Data>, navController: NavHostController) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("Details screen/${movieList[itemIndex].id}")
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = movieList[itemIndex].poster,
                contentDescription = movieList[itemIndex].title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.7f))
                    .padding(6.dp)
            ) {
                Text(
                    text = movieList[itemIndex].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = TextStyle(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.align(Alignment.End)) {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
                    Text(
                        text = movieList[itemIndex].imdb_rating.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(searchQuery: String, onSearchQueryChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White.copy(alpha = 0.4f))
            .padding(8.dp)
            .offset(y = 16.dp)
    ) {
        Text(
            text = "Movie App",
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query -> onSearchQueryChanged(query) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            placeholder = { Text("Search movies...") },
            singleLine = true
        )
    }
}
