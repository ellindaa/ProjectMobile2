package id.ellinda.myapplication

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ellinda.myapplication.navigation.Navigation
import id.ellinda.myapplication.ui.theme.MovieAppTheme
import id.ellinda.myapplication.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {

                // TODO: Setflag untuk sistem agar tampilan aplikasi full screen
                // Windowcompact untuk menyesuaikan tampilan dengan layout tanpa batasan sistem
                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                // TODO: Membuat brush untuk latar belakang dengan efek gradien linear
                val linearGradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFB226E1),
                        Color(0xFFFC6603),
                        Color(0xFF5995EE),
                        Color(0xFF303535)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )

                // TODO: Menampilkan konten aplikasi
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TODO: Menginisialisasi viewModel dan mengambil state aplikasi
                    val movieViewModel = viewModel<MovieViewModel>()
                    val state = movieViewModel.state

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(linearGradientBrush)) {
                            Navigation() // Navigasi untuk berpindah antar layar
                        }
                }
            }
        }
    }
}
