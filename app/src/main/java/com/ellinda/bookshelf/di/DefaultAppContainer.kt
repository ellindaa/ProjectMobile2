package com.ellinda.bookshelf.di

import com.ellinda.bookshelf.data.BookshelfRepository
import com.ellinda.bookshelf.data.DefaultBookshelfRepository
import com.ellinda.bookshelf.network.BookshelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// kelas implementasi dari interface AppContainer yang bertanggung jawab untuk menyediakan instance dari  BookshelfApiService dan  BookshelfRepository
class DefaultAppContainer : AppContainer {
    // start dinonaktifkan
//    private val json = Json {
//        ignoreUnknownKeys = true
//        explicitNulls = false
//    }
    // end dinonaktifkan
    override val bookshelfApiService: BookshelfApiService by lazy {
        // by lazy, properti ini akan diinisialisasi saat pertama kali di akses
        Retrofit.Builder()
        // membuat builder untuk retrofit
            .addConverterFactory(GsonConverterFactory.create())
            // menambahkan converter utk mengkonversi JSON ke objek kotlin menggunakan Gson
            // start dinonaktifkan
//            .addConverterFactory(json
//                    .asConverterFactory("application/json".toMediaType()))
            // end dinonaktifkan
            .baseUrl(BookshelfApiService.BASE_URL)
            // menetapkan URL dasar untuk API
            .build()
            // membangun instance Retrofit
            .create()
            // membuat instance dari BookshelfApiService
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        DefaultBookshelfRepository(bookshelfApiService)
    }
}
