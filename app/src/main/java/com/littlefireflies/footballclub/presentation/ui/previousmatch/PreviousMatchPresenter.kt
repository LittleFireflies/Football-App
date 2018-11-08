package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class PreviousMatchPresenter<V: PreviousMatchContract.View> @Inject
constructor(private val matchListUseCase: MatchListUseCase, val leagueListUseCase: LeagueListUseCase, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), PreviousMatchContract.UserActionListener<V>{

    override fun getLeagueList() {
        disposable.add(
                leagueListUseCase.getSoccerLeagueList()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayLeagueList(it)
                        }, {
                            view?.displayErrorMessage("Unable to load league data")
                        })
        )
    }

    override fun getMatchList() {
        view?.showLoading()
        disposable.add(
                matchListUseCase.getPreviousMatchList(view?.selectedLeague?.leagueId)
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