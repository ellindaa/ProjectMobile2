package com.ellinda.bookshelf

import android.app.Application
import com.ellinda.bookshelf.di.AppContainer
import com.ellinda.bookshelf.di.DefaultAppContainer

class BookshelfApplication: Application() {
    // Instance AppContainer yang digunakan oleh kelas lainnya untuk mendapatkan dependensi
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
