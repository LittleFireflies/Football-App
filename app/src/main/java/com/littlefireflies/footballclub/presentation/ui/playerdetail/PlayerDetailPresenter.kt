package com.littlefireflies.footballclub.presentation.ui.playerdetail

import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
class PlayerDetailPresenter<V : PlayerDetailContract.View>
constructor(private val playerRepository: PlayerRepository) : BasePresenter<V>(), PlayerDetailContract.UserActionListener<V> {

    override fun getPlayerDetail(playerId: String) {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = playerRepository.getPlayerDetail(playerId)
                view?.displayPlayer(data)
                view?.hideLoading()
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessage("Unable to load player data")
            }
        }
    }
}