package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class PreviousMatchPresenter<V: PreviousMatchContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), PreviousMatchContract.UserActionListener<V>{

    @Inject
    lateinit var matchListUseCase: MatchListUseCase

    override fun getMatchList() {
        view?.showLoading()
        disposable.add(
                matchListUseCase.getPreviousMatchList(Constants.LEAGUE_ID)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayMatchList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}