package com.littlefireflies.footballclub.ui.MatchSchedule

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class MatchSchedulePresenter<V: MatchScheduleContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: AppSchedulerProvider) : BasePresenter<V>(dataManager, disposable, schedulerProvider), MatchScheduleContract.UserActionListener<V>{
}