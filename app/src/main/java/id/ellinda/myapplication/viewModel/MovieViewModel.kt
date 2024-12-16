package id.ellinda.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ellinda.myapplication.models.Data
import id.ellinda.myapplication.models.Details
import id.ellinda.myapplication.paging.PaginationFactory
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    // TODO: inisialisasi repository untuk mendapatkan data film
    private val repository = Repository()

    // TODO: menyimpan dan mengelola state dari tampilan
    var state by mutableStateOf(ScreenState())

    // TODO: Menyimpan id untuk request detail berdasarkan id
    var id by mutableStateOf(0)

    // TODO: Pagination untuk memuat data secara bertahap
    private val pagination = PaginationFactory(
        initialPage = state.page, // Halaman awal
        onLoadUpdated = {
            // TODO: Memperbarui status loading saat data dimuat
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            // TODO: Meminta data film untuk halaman selanjutnya
            repository.getMovieList(nextPage)
        },
        getNextKey = {
            // TODO: Mendapatkan halaman berikutnya
            state.page + 1
        },
        onError = {
            // TODO: Menangani error
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newPage ->
            // TODO: Memperbarui state dengan data film yang berhasil dimuat
            state = state.copy(
                movies = state.movies + items.data,
                page = newPage,
                endReached = newPage >= 25
            )
        }
    )

    /*init {
        viewModelScope.launch {
            val response = repository.getMovieList(state.page)
            state = state.copy(
                movies = response.body()!!.data
            )
        }
    }*/

    // TODO: Inisialisasi dengan memuat item pertama pada page
    init {
        loadNextItems()
    }

    // TODO: Fungsi untuk memuat halaman berikutnya
    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    // TODO: Fungsi untuk mendapatkan detail film berdasarkan id
    fun getDetailsById() {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsById(id = id) // Mengambil detail film
                if (response.isSuccessful) {
                    // TODO: Menyimpan data detail film ke dalam state
                    state = state.copy(detailsData = response.body()!!)
                }
            } catch (e: Exception) {
                // TODO: Menangani error saat mengambil detail film
                state = state.copy(error = e.message)
            }
        }
    }
}

// TODO: Data class untuk menyimpan state dari tampilan
data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val detailsData: Details = Details(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)