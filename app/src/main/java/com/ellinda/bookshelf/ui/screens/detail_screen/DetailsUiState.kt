package com.ellinda.bookshelf.ui.screens.detail_screen

import com.ellinda.bookshelf.model.Book

sealed interface DetailsUiState {
    data class Success(val bookItem: Book) : DetailsUiState
    object Error : DetailsUiState
    object Loading : DetailsUiState
}