package com.littlefireflies.footballclub

import android.app.Application
import com.littlefireflies.footballclub.di.component.ApplicationComponent
import com.littlefireflies.footballclub.di.component.DaggerApplicationComponent
import com.littlefireflies.footballclub.di.module.ApplicationModule
import com.littlefireflies.footballclub.di.module.NetworkModule

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class App: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule())
                .build()
        applicationComponent.inject(this)
    }
}