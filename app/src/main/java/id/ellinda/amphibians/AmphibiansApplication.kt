package id.ellinda.amphibians

import android.app.Application
import id.ellinda.amphibians.data.AppContainer
import id.ellinda.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    // Instance AppContainer yang digunakan oleh kelas lainnya untuk mendapatkan dependensi 
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
