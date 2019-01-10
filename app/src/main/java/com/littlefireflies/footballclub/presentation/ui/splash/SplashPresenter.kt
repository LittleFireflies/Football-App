package com.littlefireflies.footballclub.presentation.ui.splash

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
class SplashPresenter<V : SplashContract.View>
constructor(private val leagueRepository: LeagueRepository) : BasePresenter<V>(), SplashContract.UserActionListener<V> {

    override fun getLeagueList() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = leagueRepository.getSoccerLeagueList()
            leagueRepository.saveLeagueList(data)
            view?.openActivity()
        }
    }
}