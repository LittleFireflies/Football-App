package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
interface TeamListContract {
    interface View : BaseView {
        var selectedLeague: League
        fun showLoading()
        fun hideLoading()
        fun displayTeamList(teams: List<Team>)
        fun displayErrorMessage(message: String)
        fun displayLeagueList(leagues: List<League>)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getLeagueList()
        fun getTeamList()
    }
}