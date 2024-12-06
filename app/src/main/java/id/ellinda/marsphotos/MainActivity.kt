package id.ellinda.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import id.ellinda.marsphotos.ui.MarsPhotosApp
import id.ellinda.marsphotos.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        // metode onCreate dipanggil saat aktivitas dibuat
        super.onCreate(savedInstanceState)
        // memanggil metode onCreate dari kelas induk untuk memastikan bahwa semua inisialisasi yang diperlukan dilakukan
        setContent {
            MarsPhotosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MarsPhotosApp()
                    // memanggil fungsi komposabel yang merupakan fungsi utama untuk membangun UI user aplikasi
                }
            }
        }
    }
}
