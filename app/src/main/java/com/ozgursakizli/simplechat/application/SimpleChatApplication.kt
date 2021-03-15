package com.ozgursakizli.simplechat.application

import android.app.Application
import com.ozgursakizli.simplechat.di.dataModule
import com.ozgursakizli.simplechat.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SimpleChatApplication : Application() {

    companion object {
        @Volatile
        private lateinit var INSTANCE: SimpleChatApplication

        fun getInstance(): SimpleChatApplication = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidLogger()
            androidContext(this@SimpleChatApplication)
            modules(listOf(presentationModule, dataModule))
        }
    }

}