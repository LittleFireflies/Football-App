package com.littlefireflies.footballclub.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.littlefireflies.footballclub.data.repository.league.LeagueDataStore
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchDataStore
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.player.PlayerDataStore
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.data.repository.team.TeamDataStore
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.di.scope.PerActivity
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

    @Provides
    @PerActivity
    fun provideMatchRepository(matchDataStore: MatchDataStore): MatchRepository = matchDataStore

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