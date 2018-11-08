package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.App
import com.littlefireflies.footballclub.data.network.NetworkService
import com.littlefireflies.footballclub.di.module.ApplicationModule
import com.littlefireflies.footballclub.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    val networkService: NetworkService
}