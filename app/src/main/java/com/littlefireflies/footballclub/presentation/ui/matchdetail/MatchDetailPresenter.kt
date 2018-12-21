package com.littlefireflies.footballclub.presentation.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V : MatchDetailContract.View> @Inject
constructor(
        private val matchRepository: MatchRepository,
        private val teamRepository: TeamRepository,
        disposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider)
    : BasePresenter<V>(disposable, schedulerProvider), MatchDetailContract.UserActionListener<V> {

    override fun getMatchDetail(matchId: String) {
        view?.showLoading()
        disposable.add(
                matchRepository.getMatchDetail(matchId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .doOnSuccess {
                            view?.displayMatch(it)
                        }
                        .doOnError {
                            view?.displayErrorMessages("Unable to load the data")
                        }
                        .flatMap {
                            matchRepository.isFavorite(it.matchId.toString())
                        }
                        .doOnSuccess {
                            view?.displayFavoriteStatus(it)
                            view?.hideLoading()
                        }
                        .doOnError {
                            view?.displayErrorMessages("Error")
                        }
                        .subscribe()
        )
    }

    override fun getHomeTeamImage(teamId: String?) {
        disposable.add(
                teamRepository.getTeamDetail(teamId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayHomeBadge(it.teamBadge)
                        }, {
                            view?.displayErrorMessages("Failed to load image")
                        })
        )
    }

    override fun getAwayTeamImage(teamId: String?) {
        disposable.add(
                teamRepository.getTeamDetail(teamId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayAwayBadge(it.teamBadge)
                        }, {
                            view?.displayErrorMessages("Failed to load image")
                        })
        )
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