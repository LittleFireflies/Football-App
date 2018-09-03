package com.littlefireflies.footballclub.utils.rx


import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by mrari on 02/03/2018.
 */

class AppSchedulerProvider : SchedulerProvider {

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }
}
