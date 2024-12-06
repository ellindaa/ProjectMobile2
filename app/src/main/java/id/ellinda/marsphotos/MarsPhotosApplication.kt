package id.ellinda.marsphotos

import android.app.Application
import id.ellinda.marsphotos.data.AppContainer
import id.ellinda.marsphotos.data.DefaultAppContainer

// mendeklarasikan kelas MarsPhotoApplication yang mewarisi dari Application
// kelas ini berfungsi sebagai kelas aplikasi yg dpt mengelola status & dependensi apl secara keseluruhan
class MarsPhotosApplication : Application() {
    // lateinit berarti properti ini akan diinisialisasi nanti sebelum digunakan
    // properti ini akan digunakan oleh kelas lain untuk mengakses dependensi
    lateinit var container: AppContainer
    override fun onCreate() {
        // container = DefaultAppContainer() menginisialisasi properti container dengan instance dari Default AppContainer
        // aplikasi sekarang memiliki akses ke semua dependensi yang dikelola oleh DefaultAppContainer
        super.onCreate()
        container = DefaultAppContainer()
    }
}
