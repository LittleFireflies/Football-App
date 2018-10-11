package com.littlefireflies.footballclub.presentation.ui.favoriteteam

import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
interface FavoriteTeamContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayFavoriteTeamList(teams: List<FavoriteTeam>)
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getFavoriteTeamList()
    }
}