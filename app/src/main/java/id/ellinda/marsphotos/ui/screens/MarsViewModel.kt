package id.ellinda.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.ellinda.marsphotos.MarsPhotosApplication
import id.ellinda.marsphotos.data.MarsPhotosRepository
import id.ellinda.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// antarmuka yang hanya dapat memiliki subtipe yang didefinisikan di dalamnya
// digunakan untuk merepresentasikan status UI dari layar utama aplikasi
sealed interface MarsUiState {
    // menyimpan daftar foto mars yang berhasil diambil
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    // terjadi kesalahan saat mengambil data
    object Error : MarsUiState
    // data sedang dimuat
    object Loading : MarsUiState
}

// kelas yang mewarisi ViewModel, dapat mengelola data UI dan logika bisnis untuk layar yang terkait dengan foto mars
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    // marsUiState, properti yang menyimpan status UI saat ini
    // menggunakan mutableStateOf untuk membuatnya dapat dipantau oleh Jetpack Compose
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
    private set
    // menyembunikan setter dari luar kelas, sehingga hanya dapat diubah dari dalam MarsViewModel

    // metode getMarsPhotos dipanggil untuk mengambil data foto Mars
    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        // Coroutine, metode dijalankan dalam viewModelScope yeng memungkinkan untuk meluncurkan coroutine yang terikat pada siklus hidup ViewModel
        viewModelScope.launch {
            // status UI diatur ke Loading sebelum memulai perintaan jaringan
            marsUiState = MarsUiState.Loading
            // mengambil foto mars dari marsPhotoRepository
            // jika berhasil, status diubah menjadi success dengan daftar foto
            // jika terjadi kesalahan status diubah menjadi error
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }

    // companion objek, cara untuk mendefinisikan anggota statis dalam kotlin
    // Factory adalah objek akan digunakan untuk membuat instance MarsViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
