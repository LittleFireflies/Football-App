package com.littlefireflies.footballclub.presentation.ui.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface TeamDetailContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayTeam(team: Team)
        fun displayErrorMessage(message: String)
        fun displayFavoriteStatus(favorite: Boolean)
        fun onAddToFavorite()
        fun onRemoveFromFavorite()
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getTeamDetail(teamId: String)
        fun addToFavorite(team: Team)
        fun removeFromFavorite(team: Team)
    }
}