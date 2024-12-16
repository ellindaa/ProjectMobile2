package id.ellinda.myapplication.utils

import id.ellinda.myapplication.domain.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO : Retrofit instance untuk menghubungkan Aplikasi dengan API
// Retrofit instance digunakan untuk berkomunikasi dengan API dan mendapatkan data
// Pada objek ini menghubungkan Base URL ke Retrofit
object RetrofitInstance {
    val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.Base) // Menghubungan base URL dengan rerofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java) // Membuat instance dari ApiInterface
    }
}