package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.data.network.NetworkService
import com.littlefireflies.footballclub.di.module.ActivityModule
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.presentation.ui.matchschedule.MatchScheduleActivity
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersFragment
import dagger.Component


/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val networkService: NetworkService

    fun inject(matchScheduleActivity: MatchScheduleActivity)
    fun inject(matchScheduleActivity: NextMatchFragment)
    fun inject(previousMatchFragment: PreviousMatchFragment)
    fun inject(matchDetailActivity: MatchDetailActivity)
    fun inject(favoriteMatchFragment: FavoriteMatchFragment)
    fun inject(teamDetailActivity: TeamDetailActivity)
    fun inject(teamPlayersFragment: TeamPlayersFragment)

}