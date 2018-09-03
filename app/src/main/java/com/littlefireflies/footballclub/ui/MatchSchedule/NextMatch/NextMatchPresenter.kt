package com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */

class NextMatchPresenter<V: NextMatchContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(dataManager, disposable, schedulerProvider), NextMatchContract.UserActionListener<V> {

    override fun getMatchList() {

    }
}