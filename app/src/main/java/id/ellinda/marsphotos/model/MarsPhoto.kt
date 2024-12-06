package id.ellinda.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// anotasi untuk mengubah data menjadi format yang bisa dikirim ke server atau disimpan, dan bisa mengubahnya kembali menjadi objek yang bisa digunakan di aplikasi.
@Serializable
// Data Class MarsPhoto
data class MarsPhoto(
    val id: String,
    // petunjuk untuk memberi tahu bahwa saat mengubah data ini menjadi format lain (JSON), kita akan menggunakan nama img_src untuk properti ini.
    @SerialName(value = "img_src")
    // tempat untuk menyimpan URL gambar dari foto mars 
    val imgSrc: String
)
