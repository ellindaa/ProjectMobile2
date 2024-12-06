
package id.ellinda.amphibians.data

import id.ellinda.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// Kontainer Injeksi Ketergantungan pada tingkat aplikasi.
interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

 // Implementasi container Dependency Injection di tingkat aplikasi.
 // Variabel diinisialisasi dengan lambat dan instance yang sama dibagikan ke seluruh aplikasi.
class DefaultAppContainer : AppContainer {
    // URL untuk API yang akan digunakan oleh Retrofit
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        // mengonversi JSON ke objek kotlin menggunakan kotlinx.serialization
        .baseUrl(BASE_URL)
        // menetapkan URL untuk semua panggilan API yang akan dilakukan oleh Retrofit
        .build()
        // membangun instance Retrofit

     // Retrofit objek layanan untuk membuat panggilan api
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

     // Implementasi DI untuk repositori Amfibi
    override val amphibiansRepository: AmphibiansRepository by lazy {
        DefaultAmphibiansRepository(retrofitService)
    }
}
