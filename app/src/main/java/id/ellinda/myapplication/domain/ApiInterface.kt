package id.ellinda.myapplication.domain

import id.ellinda.myapplication.models.Details
import id.ellinda.myapplication.models.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    // TODO: mengambil daftar film
    // @GET movies? -> mengakses data daftar film dari API sesuai dengan halaman dengan perintah @Query page
    // @Query("page") -> mengambil halaman
    @GET("movies?")
    suspend fun getMovies(
       @Query("page")page: Int
    ):Response<MoviesList>

    // TODO: mengambil detail film
    // @GET("movies/{movie_id}") mengambil data berdasarkan id  untuk menampilkan details dari film tersebut
    @GET("movies/{movie_id}")
    suspend fun getDetailsById(
        @Path("movie_id")id: Int
    ):Response<Details>
}