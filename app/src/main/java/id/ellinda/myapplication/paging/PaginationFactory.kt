package id.ellinda.myapplication.paging

import retrofit2.Response

class PaginationFactory<Key, Item>(
    private  val initialPage: Key, // TODO: menyimpan halaman awal sbg kunci utk memulai pagination 
    private inline val onLoadUpdated:(Boolean)-> Unit, // TODO: fungsi callback utk mengindikasikasn stts proses pemuatan
    private inline val onRequest:suspend (nextPage: Key)-> Response<Item>, // TODO: fungsi suspend utk melakukan permintaan data berdasarkan nextpage
    private inline val getNextKey:suspend (Item)-> Key, // TODO: fungsi suspend utk menghitung kunci nextpage berdasarkan data yg diterima
    private inline val onError:suspend (Throwable?)-> Unit, // TOOD: fungsi callback utk menangani kesalahan
    private inline val onSuccess:suspend (items: Item, newPage: Key)-> Unit, // TODo: fungsi callback utk menangani data yg berhasil dimuat dan mmeperbarui page

    ):Pagination<Key,Item> { // TODO: mengimplementasikan antarmuka pagination utk mendukung navigasi antar halaman
    private var currentKey = initialPage // TODO: menyimpan kunci halaman saat ini
    private var isMakingRequest = false // TODO: menyimpan stts apakah sedang ada request data yang berjalan

    override suspend fun loadNextPage() {
        if (isMakingRequest){
            return // TOOD: menghentikan proses jk sudah ada permintaan data
        }
        isMakingRequest = true /
        onLoadUpdated(true)
        // TODO: meminta data berdasarkan halaman saat ini
        // menangani respons sukses dan gagal
        try {
            val response = onRequest(currentKey)
            if (response.isSuccessful){
                isMakingRequest = false
                val items = response.body()!!
                currentKey = getNextKey(items)!!
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
        }catch (e:Exception){
            onError(e)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentKey = initialPage
    }

}
