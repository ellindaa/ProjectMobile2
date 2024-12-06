package id.ellinda.marsphotos.data

import id.ellinda.marsphotos.network.MarsApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

// perjanjian bahwa setiap kelas yang mengimplementasikan AppContainer harus memiliki marsPhotosRepository
// membantu untuk mengatur dan mengelola berbagai bagian dari apliakasi, khususnya yang berhubungan dengan data.
interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}


class DefaultAppContainer : AppContainer {
    // alamat dasar dari API yang digunakan 
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    // mengatur retrofit (alat untuk berkomunikasi dengan API)
    private val retrofit: Retrofit = Retrofit.Builder()
    // mengatur cara mengubah data dari format JSON menjadi objek yang dapat digunakan pada aplikasi
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // rerofitService adalah objek yang akan digunakan untuk berkomunikasi dengan API
    // by lazy, objek hanya akan dibuat saat dibutuhkan 
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    // marsPhotoRepository, tempat mengambil data dari API
    // menggunakan NetworkMarsPhotosRepository yang memanfaatkan retrofitService untuk mengambil data dari API
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}
