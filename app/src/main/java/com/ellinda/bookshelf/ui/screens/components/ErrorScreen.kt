package com.ellinda.bookshelf.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ellinda.bookshelf.R
import com.ellinda.bookshelf.ui.theme.BookshelfTheme

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    // sebuah lambda yang akan dipanggil ketika user menekan tombol coba lagi
    modifier: Modifier = Modifier
) {
    Column(
        // menyusun elemen UI secara vertikal
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            // menampilkan gambar yg menunjukkan kesalahan koneksi
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            // menampilkan pesan kesalahan yang diambil dari string
            stringResource(R.string.failed_to_load_msg),
            fontWeight = FontWeight.Bold
        )
        Button(onClick = retryAction) {
            // tombol untuk mencoba lagi
            Text(stringResource(R.string.btn_try_again))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    BookshelfTheme {
        ErrorScreen(retryAction = { })
    }
}
