package com.littlefireflies.footballclub.ui.MatchDetail

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V: MatchDetailContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(dataManager, disposable, schedulerProvider), MatchDetailContract.UserActionListener<V>{
    override fun getMatchDetail(matchId: String) {
        view?.showLoading()
        disposable.add(
                dataManager.getMatchDetail(matchId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayMatch(it.events[0])
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                        })
        )
    }
}