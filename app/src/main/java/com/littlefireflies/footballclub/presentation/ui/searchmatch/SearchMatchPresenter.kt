package com.littlefireflies.footballclub.presentation.ui.searchmatch

import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 13/10/18
 */
class SearchMatchPresenter<V: SearchMatchContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), SearchMatchContract.UserActionListener<V>{

    @Inject
    lateinit var matchListUseCase: MatchListUseCase

    override fun searchMatch(matchName: String) {
        view?.showLoading()
        disposable.add(
                matchListUseCase.getMatchSearchResult(matchName)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayMatch(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                        })
        )
    }
}