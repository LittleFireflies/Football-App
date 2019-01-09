package com.littlefireflies.footballclub.presentation.ui.searchmatch

import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Widyarso Joko Purnomo on 13/10/18
 */
class SearchMatchPresenter<V : SearchMatchContract.View>
constructor(private val matchRepository: MatchRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), SearchMatchContract.UserActionListener<V> {

    override fun searchMatch(matchName: String) {
        view?.showLoading()
        disposable.add(
                matchRepository.getMatchSearchResult(matchName)
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