package com.littlefireflies.footballclub.presentation.teamdetail

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
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getTeamDetail(teamId: String)
    }
}