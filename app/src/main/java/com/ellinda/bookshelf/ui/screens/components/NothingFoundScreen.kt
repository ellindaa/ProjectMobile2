package com.ellinda.bookshelf.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun NothingFoundScreen(
    modifier: Modifier = Modifier
) {
    Column(
        // menyusun elemen UI secara vertikal
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            // menampilkan gambar yg menunjukkan bahwa tdk ada data g ditemukan
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_not_found),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            // menampilkan pesan bahwa tidak ada catatan yang ditemukan 
            stringResource(R.string.no_records_found_msg),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NothingFoundScreenPreview() {
    BookshelfTheme {
        NothingFoundScreen()
    }
}
