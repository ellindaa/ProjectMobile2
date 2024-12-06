package com.ellinda.bookshelf.ui.screens.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ellinda.bookshelf.R
import com.ellinda.bookshelf.model.Book
import com.ellinda.bookshelf.ui.screens.components.ErrorScreen
import com.ellinda.bookshelf.ui.screens.components.LoadingScreen

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel, // mengelola status UI untuk detail buku
    retryAction: () -> Unit, // lambda yg akan dipanggil ketika user ingin mencoba memuat data lagi
) {
    val uiStateDet = viewModel.uiStateDetail.collectAsState().value
    // mengumpulkan status UI dari VM dan menyimpannya dalam variabel uiState

    when (uiStateDet) {
        // menggunakan pernyataan when untuk menentukan tindakan berdasarkan status UI
        is DetailsUiState.Loading -> {
            LoadingScreen()
        }
        is DetailsUiState.Error -> {
            ErrorScreen(
                retryAction = retryAction
            )
        }
        is DetailsUiState.Success -> {
            BookDetails(uiStateDet.bookItem)
        }
    }

}


@Composable
fun BookDetails(book: Book) {
    Card(
        // Card untuk membungkus konten buku
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        shape = RoundedCornerShape(8.dp),
        //border = BorderStroke(2.dp, Color.Green),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            // Column untuk menyusun elemen UI secara vertikal di dalam kartu
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                // judul buku dengan gaya teks besar
                text = "Title: " + book.volumeInfo.title,
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                // AsynImage dari coil utk memuat gambar thumbnail buku secara asinkron
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks?.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Spacer untuk memberikan ruang vertikal antara elemen UI
            // menampilkan subtitle buku
            Text(
                text = stringResource(R.string.book_subtitle, book.volumeInfo.subtitle),
                style = MaterialTheme.typography.titleMedium
            )
            // menampilkan daftar penulis buku
            Text(
                text = stringResource(R.string.book_authors, book.volumeInfo.allAuthors()),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // menampilkan harga buku
            Text(
                text = stringResource(R.string.book_price, book.saleInfo.getPrice2),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // menampilkan negara tempat buku dijual
            Text(
                text = "country: " + book.saleInfo.country,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // menampilkan harga daftar buku
            Text(
                text = "listPrice: " + book.saleInfo.getPrice2,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // menampilkan deskripsi buku 
            Text(
                text = "description: " + book.volumeInfo.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

