package com.ellinda.bookshelf.data

import com.ellinda.bookshelf.model.Book
import com.ellinda.bookshelf.network.BookshelfApiService

// Implementasi Default repositori yang mengambil data volume dari sumber data 
class DefaultBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService
    // instance bookshelfApiService digunakan untuk melakukan panggilan API
) : BookshelfRepository {
    // Mengambil daftar Volume dari sumber data pokok
    override suspend fun getBooks(query: String): List<Book>? {
        // metode getBooks mengimplementasikan funsi interface repository untuk mengambil daftar buku berdasarkan query yang diberikan
        return try {
            // try-catch block digunakan untuk menangani kemungkinan kesalahan saat melakukan panggilan API
            val res = bookshelfApiService.getBooks(query)
            // memeriksa apakah respons dari API
            if (res.isSuccessful) {
                res.body()?.items ?: emptyList()
                // jika berhasil mengembalikan daftar buku 
            } else {
                emptyList()
                // jika respons tidak berhasil, mengembalikan daftar kosong
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
            // jika terjadi kesalahan, mencetak stack trace kesalahan dan mengembalikan null
        }
    }

    // suspend menunjukkan bahwa metode ini dapat dipanggil dari dlm coroutine
    override suspend fun getBook(id: String): Book? {
        return try {
            val res = bookshelfApiService.getBook(id)
            if (res.isSuccessful) {
                res.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
