package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.data.repository.MatchDataStore
import com.littlefireflies.footballclub.data.repository.MatchRepository
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.domain.matchlist.MatchListInteractor
import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.ui.favoritematch.FavoriteMatchContract
import com.littlefireflies.footballclub.ui.favoritematch.FavoriteMatchPresenter
import com.littlefireflies.footballclub.ui.matchdetail.MatchDetailContract
import com.littlefireflies.footballclub.ui.matchdetail.MatchDetailPresenter
import com.littlefireflies.footballclub.ui.matchschedule.MatchScheduleContract
import com.littlefireflies.footballclub.ui.matchschedule.MatchSchedulePresenter
import com.littlefireflies.footballclub.ui.nextmatch.NextMatchContract
import com.littlefireflies.footballclub.ui.nextmatch.NextMatchPresenter
import com.littlefireflies.footballclub.ui.previousmatch.PreviousMatchContract
import com.littlefireflies.footballclub.ui.previousmatch.PreviousMatchPresenter
import com.littlefireflies.footballclub.utils.rx.AppSchedulerProvider
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

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
    fun provideMatchListUseCase(matchListInteractor: MatchListInteractor): MatchListUseCase = matchListInteractor

    @Provides
    @PerActivity
    fun provideMatchRepository(matchDataStore: MatchDataStore): MatchRepository = matchDataStore

    @PerActivity
    @Provides
    fun provideMatchSchedulePresenter(presenter: MatchSchedulePresenter<MatchScheduleContract.View>): MatchScheduleContract.UserActionListener<MatchScheduleContract.View> = presenter

    @PerActivity
    @Provides
    fun provideNextMatchPresenter(presenter: NextMatchPresenter<NextMatchContract.View>): NextMatchContract.UserActionListener<NextMatchContract.View> = presenter

    @PerActivity
    @Provides
    fun providePreviousMatchPresenter(presenter: PreviousMatchPresenter<PreviousMatchContract.View>): PreviousMatchContract.UserActionListener<PreviousMatchContract.View> = presenter

    @PerActivity
    @Provides
    fun provideMatchDetailPresenter(presenter: MatchDetailPresenter<MatchDetailContract.View>): MatchDetailContract.UserActionListener<MatchDetailContract.View> = presenter

    @PerActivity
    @Provides
    fun provideFavoriteMatchPresenter(presenter: FavoriteMatchPresenter<FavoriteMatchContract.View>): FavoriteMatchContract.UserActionListener<FavoriteMatchContract.View> = presenter
}