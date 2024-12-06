package com.ellinda.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
// kelas data Book merepresentasikan informasi buku dengan properti id, description, volumeInfo, saleInfo
data class Book(
    val id: String,
    val description: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
) {
    // mmetode getPrice() ini mengembalikan harga buku dalam format string
    fun getPrice() : String {
        if (saleInfo.listPrice == null) {
            return ""
        }
        return "${saleInfo.listPrice.amount} ${saleInfo.listPrice.currency}"
    }
}

@Serializable
// kelas data VolumeInfo merepresentasikan informasi volume buku dengan properti title, subtitle, description, imageLinks, authors, publisher, publisheddat
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
) {
    // propoerti ini mengembalikan string yang berisi semua penulis buku dengan memanggil metode allAuthors()
    val allAuthorsx: String
        get() = allAuthors()
    // metode ini mengembalikan string yg berisi semua penulis
    fun allAuthors() : String {
        var x= ""
        for (author in authors) {
            x += "$author, "
        }
        return x.trimEnd(',', ' ')
    }
}

@Serializable
// kelas data ImageLinks merepresentasikan tautan gambar buku dengan properti
data class ImageLinks(
    val smallThumbnail: String, // tautan untuk gambar kecil
    val thumbnail: String, // tautan untuk gambar besar
) {
    val httpsThumbnail : String
        get() = thumbnail.replace("http", "https")
        // properti ini mengembalikan tautan gambar besar dengan protokol HTTPS, mengganti http dengan https
}


@Serializable
// kelas data SaleInfo merepresentasikan informasi penjualan buku dengan properti country, isEbook, listPrice
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice?
) {
    // properti ini mengembalikan string yg menunjukkan harga buku. 
    // jika listPrice adalah null ia mengembalikan N/A untuk jumlah dan mata uang
    // jika tidak, ia mengembalikan jumlah dan mata uang dari harga
    val getPrice2 : String
        get() = "${listPrice?.amount ?: "N/A"} ${listPrice?.currency ?: "N/A"}"

}

@Serializable
// kelas data ListPrice merepresentasikan informasi harga buku dengan properti amount, dan currency
data class ListPrice(
    val amount: Float?,
    val currency: String? = ""
)
