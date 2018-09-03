package com.littlefireflies.footballclub.utils.rx


import io.reactivex.Scheduler

/**
 * Created by mrari on 02/03/2018.
 */

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}
