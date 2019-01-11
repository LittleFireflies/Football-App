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
            try {
                val data = leagueRepository.getSoccerLeagueList()
                view?.displayLeagueList(data)
            } catch (e: Exception) {
                view?.displayErrorMessage("Unable to load league data")
            }
        }
    }

    override fun getTeamList() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = teamRepository.getTeamList(view?.selectedLeague?.leagueId.toString())
                view?.displayTeamList(data)
                view?.hideLoading()
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessage(e.message ?: "Unable to load team data")
            }
        }
    }

    override fun searchTeam(teamName: String) {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = teamRepository.getTeamSearchResult(teamName)
                view?.displayTeamList(data)
                view?.hideLoading()
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessage("Unable to load the data")
            }
        }
    }
}