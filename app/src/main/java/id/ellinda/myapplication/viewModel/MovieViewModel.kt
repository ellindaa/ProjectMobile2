package id.ellinda.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ellinda.myapplication.models.Data
import id.ellinda.myapplication.models.Details
import id.ellinda.myapplication.paging.PaginationFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    // Inisialisasi repository untuk mendapatkan data film
    private val repository = Repository()

    // Menyimpan dan mengelola state dari tampilan menggunakan StateFlow
    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> get() = _state.asStateFlow()

    // Menyimpan id untuk request detail berdasarkan id
    var id by mutableStateOf(0)

    // Pagination untuk memuat data secara bertahap
    private val pagination = PaginationFactory(
        initialPage = _state.value.page, // Halaman awal
        onLoadUpdated = { isLoading ->
            updateState { copy(isLoading = isLoading) }
        },
        onRequest = { nextPage ->
            // Meminta data film untuk halaman selanjutnya
            repository.getMovieList(nextPage)
        },
        getNextKey = {
            // Mendapatkan halaman berikutnya
            _state.value.page + 1
        },
        onError = { error ->
            // Menangani error
            updateState { copy(error = error?.localizedMessage) }
        },
        onSuccess = { items, newPage ->
            // Memperbarui state dengan data film yang berhasil dimuat
            updateState {
                copy(
                    movies = _state.value.movies + items.data,
                    page = newPage,
                    endReached = newPage >= 25
                )
            }
        }
    )

    // Inisialisasi dengan memuat item pertama pada halaman awal
    init {
        loadNextItems()
    }

    // Fungsi untuk memuat halaman berikutnya
    fun loadNextItems(){
//        if (_state.value.isLoading || _state.value.endReached)
//    return
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    // Fungsi untuk mendapatkan detail film berdasarkan id
    fun getDetailsById(movieId: Int) {
        id = movieId
        viewModelScope.launch {
            try {
                val response = repository.getDetailsById(id = id) // Mengambil detail film
                if (response.isSuccessful) {
                    // Menyimpan data detail film ke dalam state
                    updateState { copy(detailsData = response.body()!!) }
                }
            } catch (e: Exception) {
                // Menangani error saat mengambil detail film
                updateState { copy(error = e.message) }
            }
        }
    }

    // Fungsi pencarian film berdasarkan kata kunci
    fun searchMovies(keyword: String) {
        viewModelScope.launch {
            try {
                updateState { copy(isLoading = true) }
                val response = repository.searchMovies(keyword) // Fungsi di repository
                if (response.isSuccessful) {
                    updateState {
                        copy(
                            movies = response.body()?.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    updateState {
                        copy(
                            isLoading = false,
                            error = response.message()
                        )
                    }
                }
            } catch (e: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    // Fungsi untuk memperbarui state
    private fun updateState(transform: ScreenState.() -> ScreenState) {
        _state.value = _state.value.transform()
    }
}

// Data class untuk menyimpan state dari tampilan
data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val detailsData: Details = Details(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)
