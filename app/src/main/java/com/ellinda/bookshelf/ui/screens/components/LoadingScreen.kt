package com.ellinda.bookshelf.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ellinda.bookshelf.ui.theme.BookshelfTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        // box untuk menyusun elemen UI
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
        // komponen yang menunjukkan bahwa proses sedang berlangsung
        // menampilkan indikator pemuatan berbentuk lingkaran
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoadingScreenPreview() {
    BookshelfTheme {
        LoadingScreen()
    }
}
