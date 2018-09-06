package com.littlefireflies.footballclub.ui.matchschedule.previousmatch

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class PreviousMatchPresenter<V: PreviousMatchContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(dataManager, disposable, schedulerProvider), PreviousMatchContract.UserActionListener<V>{

    override fun getMatchList() {
        view?.showLoading()
        disposable.add(
                dataManager.getPreviousMatches(Constants.LEAGUE_ID)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayMatchList(it.events)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}