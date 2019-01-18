package com.littlefireflies.footballclub.presentation.ui.nextmatch

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */

class NextMatchPresenter<V : NextMatchContract.View>
constructor(private val matchRepository: MatchRepository, private val leagueRepository: LeagueRepository, private val context: CoroutineContextProvider = CoroutineContextProvider()) : BasePresenter<V>(), NextMatchContract.UserActionListener<V> {

    override fun getLeagueList() {
        GlobalScope.launch(context.main) {
            try {
                val data = leagueRepository.getSoccerLeagueList()
                view?.displayLeagueList(data)
            } catch (e: Exception) {
                view?.displayErrorMessage("Unable to load league data")
            }
        }
    }

    override fun getMatchList() {
        view?.showLoading()
        val leagueId = view?.selectedLeague?.leagueId
        GlobalScope.launch(context.main) {
            try {
                val data = matchRepository.getNextMatch(leagueId.toString())
                if (data.isSuccessful) {
                    if (data.code() == 200) {
                        view?.displayMatchList(data.body()?.events ?: mutableListOf())
                        view?.hideLoading()
                    } else {
                        view?.hideLoading()
                        view?.displayErrorMessage("Unable to load match data: ${data.message()}")
                    }
                } else {
                    view?.hideLoading()
                    view?.displayErrorMessage("Unable to load match data: ${data.message()}")
                }
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessage("Unable to load match data: ${e.message}")
            }
        }
    }
}