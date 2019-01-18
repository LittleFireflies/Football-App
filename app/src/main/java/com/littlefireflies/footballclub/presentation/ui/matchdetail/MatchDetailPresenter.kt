package com.littlefireflies.footballclub.presentation.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V : MatchDetailContract.View>
constructor(
        private val matchRepository: MatchRepository,
        private val teamRepository: TeamRepository,
        private val context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), MatchDetailContract.UserActionListener<V> {

    override fun getMatchDetail(matchId: String) {
        view?.showLoading()
        GlobalScope.launch(context.main) {
            try {
                val data = matchRepository.getMatchDetail(matchId)
                val favorite = matchRepository.isFavorite(matchId)

                if (data.isSuccessful) {
                    if (data.code() == 200) {
                        data.body()?.events?.get(0)?.let { view?.displayMatch(it, favorite) }
                        view?.hideLoading()
                    } else {
                        view?.hideLoading()
                        view?.displayErrorMessages("Unable to load match data. ${data.message()}")
                    }
                } else {
                    view?.hideLoading()
                    view?.displayErrorMessages("Unable to load match data. ${data.message()}")
                }
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessages("Unable to load match data. ${e.message}")
            }
        }
    }

    override fun getHomeTeamImage(teamId: String?) {
        GlobalScope.launch(context.main) {
            try {
                val data = teamRepository.getTeamDetail(teamId)
                view?.displayHomeBadge(data.teamBadge)
            } catch (e: Exception) {
                view?.displayErrorMessages("Unable to display badge")
            }
        }
    }

    override fun getAwayTeamImage(teamId: String?) {
        GlobalScope.launch(context.main) {
            try {
                val data = teamRepository.getTeamDetail(teamId)
                view?.displayAwayBadge(data.teamBadge)
            } catch (e: Exception) {
                view?.displayAwayBadge("Unable to display badge")
            }
        }
    }

    override fun addToFavorite(match: Match) {
        matchRepository.addToFavorite(match)
        view?.onAddtoFavorite()
    }

    override fun removeFromFavorite(match: Match) {
        matchRepository.removeFromFavorite(match.matchId.toString())
        view?.onRemoveFromFavorite()
    }
}