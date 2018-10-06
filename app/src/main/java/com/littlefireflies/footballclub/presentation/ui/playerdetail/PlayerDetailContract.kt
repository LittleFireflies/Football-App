package com.littlefireflies.footballclub.presentation.ui.playerdetail

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
interface PlayerDetailContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayPlayer(player: Player)
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getPlayerDetail(playerId: String)
    }
}