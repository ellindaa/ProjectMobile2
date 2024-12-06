package com.ellinda.bookshelf

import android.app.Application
import com.ellinda.bookshelf.di.AppContainer
import com.ellinda.bookshelf.di.DefaultAppContainer

class BookshelfApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}