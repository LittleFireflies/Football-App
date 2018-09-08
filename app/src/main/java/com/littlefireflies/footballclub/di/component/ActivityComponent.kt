package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.di.module.ActivityModule
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.ui.matchschedule.MatchScheduleActivity
import com.littlefireflies.footballclub.ui.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.ui.previousmatch.PreviousMatchFragment
import dagger.Component


/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(matchScheduleActivity: MatchScheduleActivity)
    fun inject(matchScheduleActivity: NextMatchFragment)
    fun inject(previousMatchFragment: PreviousMatchFragment)
    fun inject(matchDetailActivity: MatchDetailActivity)

}