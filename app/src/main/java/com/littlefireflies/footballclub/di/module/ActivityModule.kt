package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchDataStore
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchRepository
import com.littlefireflies.footballclub.data.repository.league.LeagueDataStore
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchDataStore
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.player.PlayerDataStore
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.data.repository.team.TeamDataStore
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.domain.favoritematch.*
import com.littlefireflies.footballclub.domain.leaguelist.LeagueListInteractor
import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailInteractor
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailUseCase
import com.littlefireflies.footballclub.domain.matchlist.MatchListInteractor
import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.domain.playerdetail.PlayerDetailInteractor
import com.littlefireflies.footballclub.domain.playerdetail.PlayerDetailUseCase
import com.littlefireflies.footballclub.domain.playerlist.PlayerListInteractor
import com.littlefireflies.footballclub.domain.playerlist.PlayerListUseCase
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailInteractor
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
import com.littlefireflies.footballclub.domain.teamlist.TeamListInteractor
import com.littlefireflies.footballclub.domain.teamlist.TeamListUseCase
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchContract
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailContract
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailPresenter
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchContract
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchContract
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailContract
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailPresenter
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersContract
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersPresenter
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
    fun provideTeamListUseCase(teamListInteractor: TeamListInteractor): TeamListUseCase = teamListInteractor

    @Provides
    @PerActivity
    fun provideTeamDetailUseCase(teamDetailInteractor: TeamDetailInteractor): TeamDetailUseCase = teamDetailInteractor

    @Provides
    @PerActivity
    fun providePlayerListUseCase(playerListInteractor: PlayerListInteractor): PlayerListUseCase = playerListInteractor

    @Provides
    @PerActivity
    fun providePlayerDetailUseCase(playerDetailInteractor: PlayerDetailInteractor): PlayerDetailUseCase = playerDetailInteractor

    @Provides
    @PerActivity
    fun provideLeagueListUseCase(leagueListInteractor: LeagueListInteractor): LeagueListUseCase = leagueListInteractor

    @Provides
    @PerActivity
    fun provideMatchRepository(matchDataStore: MatchDataStore): MatchRepository = matchDataStore

    @Provides
    @PerActivity
    fun provideFavoriteMatchRepository(favoriteMatchDataStore: FavoriteMatchDataStore): FavoriteMatchRepository = favoriteMatchDataStore

    @Provides
    @PerActivity
    fun provideTeamRepository(teamDataStore: TeamDataStore): TeamRepository = teamDataStore

    @Provides
    @PerActivity
    fun providePlayerRepository(playerDataStore: PlayerDataStore): PlayerRepository = playerDataStore

    @Provides
    @PerActivity
    fun provideLeagueRepository(leagueDataStore: LeagueDataStore): LeagueRepository = leagueDataStore

}