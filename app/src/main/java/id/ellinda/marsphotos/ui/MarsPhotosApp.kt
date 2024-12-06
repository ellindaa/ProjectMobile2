@file:OptIn(ExperimentalMaterial3Api::class)
// anotasi ini digunakan untuk menunjukkan bahwa kita menggunakan API eksperimental dari Material3

package id.ellinda.marsphotos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ellinda.marsphotos.R
import id.ellinda.marsphotos.ui.screens.HomeScreen
import id.ellinda.marsphotos.ui.screens.MarsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsPhotosApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // mengatur perilaku gulir dari TopAppBar
    Scaffold(
        // komponen yang menyediakan struktur dasar untuk layout, termasuk area untuk app bar, dan konten.
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            // komponen yang memberikan latar belakang dan mengatur ukuran konten didalamnya
            modifier = Modifier.fillMaxSize()
        ) {
            val marsViewModel: MarsViewModel =
                viewModel(factory = MarsViewModel.Factory)
                // mengambil instance dari MarsViewMoodel menggunakan viewModel(factory = MarsViewModel.Factory)
                // untuk mengelola dan menyimpan UI state
            HomeScreen(
                // memanggil fungsi komposabel HomeScreen dan mengoper statue UI marsUiState
                marsUiState = marsViewModel.marsUiState,
                retryAction = marsViewModel::getMarsPhotos,
                contentPadding = it
            )
        }
    }
}

@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    // fungsi untuk membangun app bar yang terletak di bagian atas aplikasi
    CenterAlignedTopAppBar(
        // judul terletak pada bagian tengah
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
