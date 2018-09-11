package com.littlefireflies.footballclub.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.ui.base.BaseView
import com.littlefireflies.footballclub.ui.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
interface MatchDetailContract {

    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayMatch(match: Match)
        fun displayHomeBadge(teamBadge: String?)
        fun displayAwayBadge(teamBadge: String?)
        fun displayFavoriteStatus(favorite: Boolean)
        fun onAddtoFavorite()
        fun onRemoveFromFavorite()
    }

    interface UserActionListener<V: View>: IBasePresenter<V> {
        fun getMatchDetail(matchId: String)
        fun getHomeTeamImage(teamId: String?)
        fun getAwayTeamImage(teamId: String?)
        fun addToFavorite(match: Match)
        fun removeFromFavorite(match: Match)
    }
}