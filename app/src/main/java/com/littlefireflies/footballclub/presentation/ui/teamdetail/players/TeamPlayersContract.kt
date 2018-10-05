package com.littlefireflies.footballclub.presentation.ui.teamdetail.players

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
interface TeamPlayersContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayPlayerList(players: List<Player>)
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getPlayerList(teamId: String?)
    }
}