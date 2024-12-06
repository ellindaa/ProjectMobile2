package id.ellinda.marsphotos.network

import id.ellinda.marsphotos.model.MarsPhoto
import retrofit2.http.GET

// method getPhotos akan mengembalikan daftar foto Mars dan dapat dipanggil dari coroutine
interface MarsApiService {
    // memberi tahu retrofit bahwa kita meminta data dari URL yang berisi photos
    // ketika memanggil metode ini, retrofit akan mengirimkan permintaan ke server untuk mendapatkan foto-foto Mars.
    @GET("photos")
    // fungsi yang akan dijalankan tanpa mengganggu proses lainnya
    suspend fun getPhotos(): List<MarsPhoto>
}
