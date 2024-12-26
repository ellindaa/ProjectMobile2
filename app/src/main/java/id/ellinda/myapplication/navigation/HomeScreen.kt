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
    val movieViewModel = viewModel<MovieViewModel>() // TODO: menginisialisasi ViewModel utk mengambil data film
    val state by movieViewModel.state.collectAsState() // TODO: mengambil data terbaru dari stateflow viewmodel

    val context = LocalContext.current // TODO: mengambil konteks lokal utk Toast
    var searchQuery by remember { mutableStateOf("") } // TODO: menyimpan nilai search
    val gridState = rememberLazyGridState() // TODO: menyimpan status scroll untuk lazyverticalgrid

    // TODo: memantau status scroll utk memuat data tambahan saat mencapai akhir
    LaunchedEffect(gridState.firstVisibleItemIndex) {
        if (gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == state.movies.size - 1) {
            movieViewModel.loadNextItems() // TODO: memuat halaman berikutnya
        }
    }

    // TODO: menampilkan UI utama dg scaffold yg memiliki topbar dan konten
    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar(searchQuery) { query ->
                searchQuery = query // TODO: memperbarui query pencarian
                movieViewModel.searchMovies(query) // TODO: memfilter film berdasarkan film yang dicari
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
                } // TODO: menyaring film berdasarkan input pencarian

                items(filteredMovies.size) { index ->
                    ItemUi(
                        itemIndex = index,
                        movieList = filteredMovies,
                        navController = navController // TODO: navigasi ke detail saat di klik
                    )
                }

                //TODO: loading saat data sedang dimuat
                if (state.isLoading) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator() // TODO: indikator loading
                        }
                    }
                }
                

                // TODO: menampilkan pesan error menggunakan Toast
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
    // TODO: menampilkan item film dg gambar dan detail singkat
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
                model = movieList[itemIndex].poster, // TODO: memuat gambar poster film
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
                    text = movieList[itemIndex].title, // TODO: menampilkan judul film 
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
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = null) // TODO: ikon rating
                    Text(
                        text = movieList[itemIndex].imdb_rating.toString(), // TODO: menampilkan rating film
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
    // TODO: menampilkan bilah pencarian di bagian atas layar
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White.copy(alpha = 0.4f))
            .padding(8.dp)
            .offset(y = 16.dp)
    ) {
        Text(
            text = "Movie App", // TODO: menampilkan judul aplikasi
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
            value = searchQuery, // TODO: input teks pencarian
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
