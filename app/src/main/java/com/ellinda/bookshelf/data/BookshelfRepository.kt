package com.ellinda.bookshelf.data

import com.ellinda.bookshelf.model.Book

// interface dari repository ini untuk mengambil data volume buku dari sumber data
interface BookshelfRepository {
    // Mengambil daftar buku dari sumber data pokok 
    suspend fun getBooks(query: String): List<Book>?
    // suspend, menunjukkan bahwa metode ini dapat dipanggil dari dalam coroutine
    // metode getBook digunakan untuk mengambil informasi tentang buku tertentu berdasarkan ID yg diberikan
    suspend fun getBook(id: String): Book?
}
