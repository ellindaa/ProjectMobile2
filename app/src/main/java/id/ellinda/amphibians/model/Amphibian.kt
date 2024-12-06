package id.ellinda.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Kelas data yang mendefinisikan amfibi yang mencakup nama, jenis, deskripsi, dan URL gambar.
@Serializable
// anotasi ini menandakan bahwa kelas Amphibian dapat diserialisasi dan deserialisasi
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src") val imgSrc: String
)
