package com.ellinda.bookshelf.ui.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ellinda.bookshelf.AppDestinations
import com.ellinda.bookshelf.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentScreen: AppDestinations, // menyimpan info tentang layar saat ini
    canNavigateBack: Boolean, // menunjukkan apakah user dpt kembali ke layar sebelumnya, jika true ikon panah kembali akan ditampilkan
    onNavigateUpClicked: () -> Unit // sebuah lambda yang akan dipanggil ketika ikon navigasi kembali ditekan
) {
    TopAppBar(
        // bilah aplikasi di bagian atas layar
        title = { Text(text = currentScreen.title) }, // judul layar saat ini
        navigationIcon = {
            if (canNavigateBack){
                IconButton(
                    onClick = onNavigateUpClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.btn_try_again)
                    )
                }
            }
        }
    )
}
