package com.littlefireflies.footballclub.di.component

import com.littlefireflies.footballclub.di.module.ActivityModule
import com.littlefireflies.footballclub.di.scope.PerActivity
import com.littlefireflies.footballclub.ui.MatchSchedule.MatchScheduleActivity
import dagger.Component


/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(matchScheduleActivity: MatchScheduleActivity)

}