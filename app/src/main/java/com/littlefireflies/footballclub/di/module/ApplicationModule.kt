package com.littlefireflies.footballclub.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@Module
class ApplicationModule(private val application: Application) {
    @Provides
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    internal fun provideApplication(): Application {
        return application
    }
}