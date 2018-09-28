package com.littlefireflies.footballclub.ui.matchschedule

import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class MatchSchedulePresenter<V: MatchScheduleContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: AppSchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), MatchScheduleContract.UserActionListener<V>{
}