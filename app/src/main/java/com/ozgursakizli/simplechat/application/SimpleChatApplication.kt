package com.ozgursakizli.simplechat.application

import android.app.Application
import com.ozgursakizli.simplechat.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimpleChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SimpleChatApplication)
            presentationModule
        }
    }

}