package com.littlefireflies.footballclub.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.domain.favoritematch.AddFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.favoritematch.GetFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.favoritematch.RemoveFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailUseCase
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V: MatchDetailContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), MatchDetailContract.UserActionListener<V>{

    @Inject
    lateinit var matchDetailUseCase: MatchDetailUseCase
    @Inject
    lateinit var getFavoriteMatchUseCase: GetFavoriteMatchUseCase
    @Inject
    lateinit var addFavoriteMatchUseCase: AddFavoriteMatchUseCase
    @Inject
    lateinit var removeFavoriteMatchUseCase: RemoveFavoriteMatchUseCase
    @Inject
    lateinit var teamDetailUseCase: TeamDetailUseCase

    override fun getMatchDetail(matchId: String) {
        view?.showLoading()
        disposable.add(
                matchDetailUseCase.getMatchDetail(matchId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .doOnSuccess {
                            view?.displayMatch(it)
                        }
                        .doOnError {
                            view?.displayErrorMessages("Unable to load the data")
                        }
                        .flatMap {
                            getFavoriteMatchUseCase.getFavoriteMatchStatus(it.matchId.toString())
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
                teamDetailUseCase.getTeamDetail(teamId)
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
                teamDetailUseCase.getTeamDetail(teamId)
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
        addFavoriteMatchUseCase.addToFavorite(match)
        view?.onAddtoFavorite()
    }

    override fun removeFromFavorite(match: Match) {
        removeFavoriteMatchUseCase.removeFromFavorite(match.matchId.toString())
        view?.onRemoveFromFavorite()
    }
}