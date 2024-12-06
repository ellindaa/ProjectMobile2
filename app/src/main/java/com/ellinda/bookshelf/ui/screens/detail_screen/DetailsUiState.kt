package com.ellinda.bookshelf.ui.screens.detail_screen

import com.ellinda.bookshelf.model.Book

// sebuah sealed interface untuk mendefinisikan hierarki tipe yang terbatas
sealed interface DetailsUiState {
    // data class yang mewakili status sukses ketika detail buku berhasil dimuat
    data class Success(val bookItem: Book) : DetailsUiState
    // objek singleton yang mewakili status kesalahan, ketika terjadi kesalahan dlm memuat detail buku status ini akan di gunakan
    object Error : DetailsUiState
    // data sedang dimual, status ini digunakan untuk menampilkan indikator loading di UI
    object Loading : DetailsUiState
}
