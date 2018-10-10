package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.domain.teamlist.TeamListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
class TeamListPresenter<V: TeamListContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider):BasePresenter<V>(disposable, schedulerProvider), TeamListContract.UserActionListener<V>{

    @Inject
    lateinit var leagueListUseCase: LeagueListUseCase
    @Inject
    lateinit var teamListUseCase: TeamListUseCase

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

    override fun getTeamList() {
        view?.showLoading()
        disposable.add(
                teamListUseCase.getTeamList(view?.selectedLeague?.leagueId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayTeamList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load team data")
                        })
        )
    }
}