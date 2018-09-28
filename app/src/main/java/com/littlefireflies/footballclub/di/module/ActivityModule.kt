package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchDataStore
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchRepository
import com.littlefireflies.footballclub.data.repository.match.MatchDataStore
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.team.TeamDataStore
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.domain.favoritematch.*
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailInteractor
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailUseCase
import com.littlefireflies.footballclub.domain.matchlist.MatchListInteractor
import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailInteractor
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
import com.littlefireflies.footballclub.presentation.favoritematch.FavoriteMatchContract
import com.littlefireflies.footballclub.presentation.favoritematch.FavoriteMatchPresenter
import com.littlefireflies.footballclub.presentation.matchdetail.MatchDetailContract
import com.littlefireflies.footballclub.presentation.matchdetail.MatchDetailPresenter
import com.littlefireflies.footballclub.presentation.matchschedule.MatchScheduleContract
import com.littlefireflies.footballclub.presentation.matchschedule.MatchSchedulePresenter
import com.littlefireflies.footballclub.presentation.nextmatch.NextMatchContract
import com.littlefireflies.footballclub.presentation.nextmatch.NextMatchPresenter
import com.littlefireflies.footballclub.presentation.previousmatch.PreviousMatchContract
import com.littlefireflies.footballclub.presentation.previousmatch.PreviousMatchPresenter
import com.littlefireflies.footballclub.presentation.teamdetail.TeamDetailContract
import com.littlefireflies.footballclub.presentation.teamdetail.TeamDetailPresenter
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
    fun provideMatchDetailUseCase(matchDetailInteractor: MatchDetailInteractor): MatchDetailUseCase = matchDetailInteractor

    @Provides
    @PerActivity
    fun provideGetFavoriteMatchUseCase(getFavoriteMatchInteractor: GetFavoriteMatchInteractor): GetFavoriteMatchUseCase = getFavoriteMatchInteractor

    @Provides
    @PerActivity
    fun provideAddFavoriteMatchUseCase(addFavoriteMatchInteractor: AddFavoriteMatchInteractor): AddFavoriteMatchUseCase = addFavoriteMatchInteractor

    @Provides
    @PerActivity
    fun provideRemoveFavoriteMatchUseCase(removeFavoriteMatchInteractor: RemoveFavoriteMatchInteractor): RemoveFavoriteMatchUseCase = removeFavoriteMatchInteractor

    @Provides
    @PerActivity
    fun provideTeamDetailUseCase(teamDetailInteractor: TeamDetailInteractor): TeamDetailUseCase = teamDetailInteractor

    @Provides
    @PerActivity
    fun provideMatchRepository(matchDataStore: MatchDataStore): MatchRepository = matchDataStore

    @Provides
    @PerActivity
    fun provideFavoriteMatchRepository(favoriteMatchDataStore: FavoriteMatchDataStore): FavoriteMatchRepository = favoriteMatchDataStore

    @Provides
    @PerActivity
    fun provideTeamRepository(teamDataStore: TeamDataStore): TeamRepository = teamDataStore

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

    @Provides
    @PerActivity
    fun provideTeamDetailPresenter(presenter: TeamDetailPresenter<TeamDetailContract.View>): TeamDetailContract.UserActionListener<TeamDetailContract.View> = presenter
}