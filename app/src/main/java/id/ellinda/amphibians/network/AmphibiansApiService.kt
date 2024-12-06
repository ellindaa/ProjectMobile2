package id.ellinda.amphibians.network

import id.ellinda.amphibians.model.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    // metode getAmphibians melakukan permintaan GET ke endpoint
    // ketika metode ini dipanggil, Retrofit akan mengirimkan permintaan HTTP GET ke URL
    suspend fun getAmphibians(): List<Amphibian>
}
