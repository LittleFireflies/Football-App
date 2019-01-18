package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class PreviousMatchPresenter<V : PreviousMatchContract.View>
constructor(private val matchRepository: MatchRepository, private val leagueRepository: LeagueRepository, private val context: CoroutineContextProvider = CoroutineContextProvider()) : BasePresenter<V>(), PreviousMatchContract.UserActionListener<V> {

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
        GlobalScope.launch(context.main) {
            try {
                val data = matchRepository.getPreviousMatch(view?.selectedLeague?.leagueId.toString())
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