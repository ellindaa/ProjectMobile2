package com.ellinda.bookshelf.di

import com.ellinda.bookshelf.data.BookshelfRepository
import com.ellinda.bookshelf.network.BookshelfApiService

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val bookshelfApiService: BookshelfApiService
    val bookshelfRepository: BookshelfRepository
}