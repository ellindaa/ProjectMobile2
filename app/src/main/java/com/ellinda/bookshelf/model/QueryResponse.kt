package com.ellinda.bookshelf.model

import kotlinx.serialization.Serializable

// Notes: the null here is KEY, we need it, in case nothing is found
@Serializable
// kelas data QueryResponse merepresentasikan respons dari kueri yang dilakukan ke API
data class QueryResponse(
    val items: List<Book>?, // menyimpan daftar buku yang ditemukan
    val totalItems: Int, // memberikan informasi jumlah total buku
    val kind: String, // memberikan konteks tambahan tentang jenis respons yg diterima
)
