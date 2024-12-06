package com.ellinda.bookshelf.network

import com.ellinda.bookshelf.model.Book
import com.ellinda.bookshelf.model.QueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * A public interface that exposes the [getBooks] method
 */
interface BookshelfApiService {
    companion object {
        // objek pendamping (companion object) yang berisi konstanta
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
        // URL untuk membangun permintaan ke endpoint yang sesuai
    }

    // Mengembalikan [List] dari [Book] dan metode ini dapat dipanggil dari Coroutine.
    // Anotasi @GET menunjukkan bahwa titik akhir "volume" akan diminta dengan GET
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): Response<QueryResponse>
    // mengambil daftar buku berdasarkan query pencarian yang diberikan

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Response<Book>
    // mengambil detail dari buku tertentu berdasarkan id buku
}
