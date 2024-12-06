package id.ellinda.amphibians.data

import id.ellinda.amphibians.model.Amphibian
import id.ellinda.amphibians.network.AmphibiansApiService

// Repositori mengambil data amfibi dari sumber data 
interface AmphibiansRepository {
    // Mengambil daftar amfibi dari sumber data 
    suspend fun getAmphibians(): List<Amphibian>
}

 // Implementasi Jaringan repositori yang mengambil data amfibi dari sumber data
class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    // Mengambil daftar amfibi dari sumber data 
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}
