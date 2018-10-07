package com.littlefireflies.footballclub.presentation.ui.nextmatch

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NextMatchContract {

    interface View: BaseView {
        var selectedLeague: League
        fun showLoading()
        fun hideLoading()
        fun displayMatchList(events: List<Match>)
        fun displayErrorMessage(message: String)
        fun displayLeagueList(leagues: List<League>)
    }

    interface UserActionListener<V: View>: IBasePresenter<V> {
        fun getLeagueList()
        fun getMatchList()
    }
}