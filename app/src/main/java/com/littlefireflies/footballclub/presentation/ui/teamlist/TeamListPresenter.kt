package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
class TeamListPresenter<V : TeamListContract.View>
constructor(private val leagueRepository: LeagueRepository, private val teamRepository: TeamRepository) : BasePresenter<V>(), TeamListContract.UserActionListener<V> {

    override fun getLeagueList() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = leagueRepository.getSoccerLeagueList()
            view?.displayLeagueList(data)
        }
    }

    override fun getTeamList() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = teamRepository.getTeamList(view?.selectedLeague?.leagueId.toString())
            view?.displayTeamList(data)
            view?.hideLoading()
        }
    }

    override fun searchTeam(teamName: String) {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = teamRepository.getTeamSearchResult(teamName)
            view?.displayTeamList(data)
            view?.hideLoading()
        }
    }
}