package com.littlefireflies.footballclub

import android.app.Application
import com.littlefireflies.footballclub.di.appModule
import com.littlefireflies.footballclub.di.networkModule
import org.koin.android.ext.android.startKoin

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, appModule))
    }
}