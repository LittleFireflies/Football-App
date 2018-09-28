package com.littlefireflies.footballclub.ui.matchdetail

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.domain.matchDetail.MatchDetailUseCase
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V: MatchDetailContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(dataManager, disposable, schedulerProvider), MatchDetailContract.UserActionListener<V>{

    @Inject
    lateinit var matchDetailUseCase: MatchDetailUseCase

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
                            dataManager.isFavorite(it.matchId.toString())
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
//        disposable.add(
//                dataManager.getMatchDetail(matchId)
//                        .subscribeOn(schedulerProvider.io())
//                        .observeOn(schedulerProvider.ui())
//                        .doOnSuccess {
//                            view?.displayMatch(it.events[0])
//                        }
//                        .doOnError{
//                            view?.displayErrorMessages("Unable to load data")
//                        }
//                        .flatMap {
//                            dataManager.isFavorite(it.events[0].matchId.toString()) }
//                        .doOnSuccess {
//                            view?.displayFavoriteStatus(it)
//                            view?.hideLoading()
//                        }
//                        .doOnError {
//                            view?.displayErrorMessages("Network error")
//                        }
//                        .subscribe()
//        )
    }

    override fun getHomeTeamImage(teamId: String?) {
        disposable.add(
                dataManager.getTeamDetail(teamId.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayHomeBadge(it.teams[0].teamBadge)
                        }, {

                        })
        )
    }

    override fun getAwayTeamImage(teamId: String?) {
        disposable.add(
                dataManager.getTeamDetail(teamId.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayAwayBadge(it.teams[0].teamBadge)
                        }, {

                        })
        )
    }

    override fun addToFavorite(match: Match) {
        dataManager.addToFavorite(match)
        view?.onAddtoFavorite()
    }

    override fun removeFromFavorite(match: Match) {
        dataManager.removeFromFavorite(match.matchId.toString())
        view?.onRemoveFromFavorite()
    }
}