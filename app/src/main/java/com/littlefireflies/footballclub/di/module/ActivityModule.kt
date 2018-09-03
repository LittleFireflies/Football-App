package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.ui.MatchSchedule.MatchScheduleContract
import com.littlefireflies.footballclub.ui.MatchSchedule.MatchSchedulePresenter
import com.littlefireflies.footballclub.utils.rx.AppSchedulerProvider
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun provideDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun provideMatchSchedulePresenter(presenter: MatchSchedulePresenter<MatchScheduleContract.View>): MatchScheduleContract.UserActionListener<MatchScheduleContract.View> {
        return presenter
    }

}