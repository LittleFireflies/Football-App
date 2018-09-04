package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.ui.MatchSchedule.MatchScheduleContract
import com.littlefireflies.footballclub.ui.MatchSchedule.MatchSchedulePresenter
import com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch.NextMatchContract
import com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch.NextMatchPresenter
import com.littlefireflies.footballclub.ui.MatchSchedule.PreviousMatch.PreviousMatchContract
import com.littlefireflies.footballclub.ui.MatchSchedule.PreviousMatch.PreviousMatchPresenter
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

    @PerActivity
    @Provides
    fun provideMatchSchedulePresenter(presenter: MatchSchedulePresenter<MatchScheduleContract.View>): MatchScheduleContract.UserActionListener<MatchScheduleContract.View> = presenter

    @PerActivity
    @Provides
    fun provideNextMatchPresenter(presenter: NextMatchPresenter<NextMatchContract.View>): NextMatchContract.UserActionListener<NextMatchContract.View> = presenter

    @PerActivity
    @Provides
    fun providePreviousMatchPresenter(presenter: PreviousMatchPresenter<PreviousMatchContract.View>): PreviousMatchContract.UserActionListener<PreviousMatchContract.View> = presenter


}