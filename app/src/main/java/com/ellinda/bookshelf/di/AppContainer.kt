package com.ellinda.bookshelf.di

import com.ellinda.bookshelf.data.BookshelfRepository
import com.ellinda.bookshelf.network.BookshelfApiService

// wadah dependencu injection pada tingkat aplikasi
interface AppContainer {
    // mengembalikan instance dari BookshelfApiService, memungkinkan bagian lain dari apl utk mengakses layanan API
    // tanpa perlu mengetauhi bagaimana layanan tsb diinisialisasi
    val bookshelfApiService: BookshelfApiService
    // mengembalikan instance dari BookshelfRepository, memungkinkan bagian lain dari apl utk mengakses repository yg mengelola pengambilan data buku
    val bookshelfRepository: BookshelfRepository
}
