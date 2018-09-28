package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.data.network.NetworkService
import com.littlefireflies.footballclub.di.module.ActivityModule
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.presentation.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.presentation.matchschedule.MatchScheduleActivity
import com.littlefireflies.footballclub.presentation.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.presentation.teamdetail.TeamDetailActivity
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

}