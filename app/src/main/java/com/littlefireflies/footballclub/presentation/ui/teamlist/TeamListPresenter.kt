package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
class TeamListPresenter<V : TeamListContract.View>
constructor(private val leagueRepository: LeagueRepository, private val teamRepository: TeamRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), TeamListContract.UserActionListener<V> {

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

    override fun getTeamList() {
        view?.showLoading()
        disposable.add(
                teamRepository.getTeamList(view?.selectedLeague?.leagueId.toString())
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

    override fun searchTeam(teamName: String) {
        view?.showLoading()
        disposable.add(
                teamRepository.getTeamSearchResult(teamName)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayTeamList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load search results")
                        })
        )
    }
}