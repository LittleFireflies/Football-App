package com.littlefireflies.footballclub.presentation.ui.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDetailPresenter<V : TeamDetailContract.View>
constructor(private val teamRepository: TeamRepository) : BasePresenter<V>(), TeamDetailContract.UserActionListener<V> {

    override fun getTeamDetail(teamId: String) {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = teamRepository.getTeamDetail(teamId)
            val favorite = teamRepository.isFavorite(teamId)

            view?.displayTeam(data)
            view?.displayFavoriteStatus(favorite)
            view?.hideLoading()
        }
    }

    override fun addToFavorite(team: Team) {
        teamRepository.addtoFavorite(team)
        view?.onAddToFavorite()
    }

    override fun removeFromFavorite(team: Team) {
        teamRepository.removeFromFavorite(team.teamId.toString())
        view?.onRemoveFromFavorite()
    }
}