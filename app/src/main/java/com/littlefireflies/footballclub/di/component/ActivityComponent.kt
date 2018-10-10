package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.data.network.NetworkService
import com.littlefireflies.footballclub.di.module.ActivityModule
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.presentation.ui.main.MainActivity
import com.littlefireflies.footballclub.presentation.ui.match.MatchFragment
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.ui.playerdetail.PlayerDetailActivity
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.presentation.ui.splash.SplashActivity
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersFragment
import com.littlefireflies.footballclub.presentation.ui.teamlist.TeamListFragment
import dagger.Component


/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val networkService: NetworkService

    fun inject(mainActivity: MainActivity)
    fun inject(matchScheduleActivity: NextMatchFragment)
    fun inject(previousMatchFragment: PreviousMatchFragment)
    fun inject(matchDetailActivity: MatchDetailActivity)
    fun inject(favoriteMatchFragment: FavoriteMatchFragment)
    fun inject(teamDetailActivity: TeamDetailActivity)
    fun inject(teamPlayersFragment: TeamPlayersFragment)
    fun inject(playerDetailActivity: PlayerDetailActivity)
    fun inject(matchFragment: MatchFragment)
    fun inject(splashActivity: SplashActivity)
    fun inject(teamListFragment: TeamListFragment)

}