package com.littlefireflies.footballclub.presentation.ui.nextmatch

import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */

class NextMatchPresenter<V: NextMatchContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), NextMatchContract.UserActionListener<V> {

    @Inject
    lateinit var matchListUseCase: MatchListUseCase

    override fun getMatchList() {
        view?.showLoading()
        disposable.add(
                matchListUseCase.getNextMatchList(Constants.LEAGUE_ID)
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