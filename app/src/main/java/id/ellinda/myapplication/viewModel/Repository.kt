package id.ellinda.myapplication.viewModel

import id.ellinda.myapplication.models.Details
import id.ellinda.myapplication.models.MoviesList
import id.ellinda.myapplication.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    //TODO
    //Class Repository ini merangkum logika yang diperlukan untuk mengakses sumber data
    //dan sebagai perantara antara API dan ViewModel
    //fungsi get MoviesList dan get DetailsbyId digunakan untuk mendapatkan data dari API
    suspend fun getMovieList(page: Int):Response<MoviesList>{
        return RetrofitInstance.api.getMovies(page)
    }
    suspend fun getDetailsById(id:Int):Response<Details> {
        return RetrofitInstance.api.getDetailsById(id = id)
    }
}
