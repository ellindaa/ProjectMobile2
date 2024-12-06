package id.ellinda.marsphotos.data

import id.ellinda.marsphotos.model.MarsPhoto
import id.ellinda.marsphotos.network.MarsApiService


interface MarsPhotosRepository {
    // suspend, menunjukkan bahwa fungsi ini dapat ditangguhkan yang berarti ia dapat dijalankan secara asinkron
    // return type, mengembalikan daftar list dari objek MarsPhoto, ketika memanggil fungsi ini akan mendapatkan koleksi foto Mars.
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

// kelas ini adalah implementasi dari interface MarsPhotoRepository
// marsApiService adalah objek dari MarsApiService yang digunakan untuk mengambil data dari API
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    // override menunjukkan bahwa kita mengimplementasikan fungsi dari interface MarsPhotosRepository
    // fungsi getMarsPhotos() memanggil fungsi marsApiService.getPhotos() yang merupakan metode dari MarsApiService untuk melakukan permintaan ke API dan mengembalikan daftar foto Mars.
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}
