package com.littlefireflies.footballclub.presentation.ui.teamdetail.players

import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class TeamPlayersPresenter<V : TeamPlayersContract.View>
constructor(private val playerRepository: PlayerRepository) : BasePresenter<V>(), TeamPlayersContract.UserActionListener<V> {

    override fun getPlayerList(teamId: String?) {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = playerRepository.getPlayers(teamId.toString())
            view?.displayPlayerList(data)
            view?.hideLoading()
        }
    }
}