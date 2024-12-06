package id.ellinda.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.ellinda.marsphotos.R
import id.ellinda.marsphotos.model.MarsPhoto
import id.ellinda.marsphotos.ui.theme.MarsPhotosTheme

@Composable
fun HomeScreen(
    // menyimpan status UI (Loading, Success, Error)
    marsUiState: MarsUiState,
    // fungsi yang akan dipanggil jika user ingin mencoba lagi
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    // logika when digunakan untuk memeriksa status marsUiState dan menampilkan layar yang sesuai
    when (marsUiState) {
        // menampilkan LoadingScreen
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        // menampilkan PhotosGridScreen dengan daftar foto
        is MarsUiState.Success -> PhotosGridScreen(
            marsUiState.photos, contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )
        // menampilkan ErrorScreen dengan opsi untuk mencoba lagi
        is MarsUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    // menampilkan gambar loading saat data sedang dimuat
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        // menggunakan Column untuk menyusun elemen secara vertikal
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // menampilkan gambar kesalahan
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        // teks yang menjelaskan kesalahan
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        // tombol untuk mencoba lagi
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}


@Composable
fun PhotosGridScreen(
    photos: List<MarsPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        // menampilkan daftar foto dalam bentuk grid yang dapat di gulir secara vertikal
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        // mengiterasi daftar foto, dan untuk setiap foto memanggil MarsPhotoCard untuk menampilkan foto tersebut
        items(items = photos, key = { photo -> photo.id }) { photo ->
            MarsPhotoCard(
                photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    // menampilkan foto dalam bentuk kartu dengan efek elevasi atau bayangan
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // digunakan untuk membuat gambar secara asinkron menggunakan Coil
        // Coil, sebuah pustaka untuk memuat gambar di Android
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        PhotosGridScreen(mockData)
    }
}
