package com.littlefireflies.footballclub.di.module

import android.app.Application
import android.content.Context
import com.littlefireflies.footballclub.data.AppDataManager
import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.data.database.AppDbHelper
import com.littlefireflies.footballclub.data.database.DbHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }
}