package com.littlefireflies.footballclub.utils

import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by widyarso.purnomo on 12/09/2018.
 */

class TestSchedulerProvider
constructor(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}