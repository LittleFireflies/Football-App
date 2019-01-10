package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class PreviousMatchPresenter<V : PreviousMatchContract.View>
constructor(private val matchRepository: MatchRepository, private val leagueRepository: LeagueRepository) : BasePresenter<V>(), PreviousMatchContract.UserActionListener<V> {

    override fun getLeagueList() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = leagueRepository.getSoccerLeagueList()
            view?.displayLeagueList(data)
        }
    }

    override fun getMatchList() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = matchRepository.getPreviousMatch(view?.selectedLeague?.leagueId.toString())
            view?.displayMatchList(data)
            view?.hideLoading()
        }
    }
}