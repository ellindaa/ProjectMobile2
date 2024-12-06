package com.ellinda.bookshelf.ui.screens.query_screen

import com.ellinda.bookshelf.model.Book

sealed interface QueryUiState {
    data class Success(val bookshelfList: List<Book>) : QueryUiState
    object Error : QueryUiState
    object Loading : QueryUiState
}