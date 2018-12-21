package com.littlefireflies.footballclub.presentation.ui.nextmatch

import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.domain.matchlist.MatchListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */

class NextMatchPresenter<V : NextMatchContract.View> @Inject
constructor(private val matchListUseCase: MatchListUseCase, private val leagueListUseCase: LeagueListUseCase, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), NextMatchContract.UserActionListener<V> {

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
        val leagueId = view?.selectedLeague?.leagueId
        disposable.add(
                matchListUseCase.getNextMatchList(leagueId)
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