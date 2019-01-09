package com.littlefireflies.footballclub.presentation.ui.nextmatch

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */

class NextMatchPresenter<V : NextMatchContract.View>
constructor(private val matchRepository: MatchRepository, private val leagueRepository: LeagueRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), NextMatchContract.UserActionListener<V> {

    override fun getLeagueList() {
        disposable.add(
                leagueRepository.getSoccerLeagueList()
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
                matchRepository.getNextMatch(leagueId.toString())
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