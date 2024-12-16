package id.ellinda.myapplication.paging

//TODO: menambahkan implementasi untuk pagination
//Key untuk menavigasi halaman
//Item untuk data yang di muat pada setiap page
//loadNextPage untuk memuat halaman berikutnya
interface Pagination <Key, Item> {
    suspend fun loadNextPage()
    fun reset()
}