package com.littlefireflies.footballclub.ui.matchdetail

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
class MatchDetailPresenter<V: MatchDetailContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(dataManager, disposable, schedulerProvider), MatchDetailContract.UserActionListener<V>{
    override fun getMatchDetail(matchId: String) {
        view?.showLoading()
        disposable.add(
                dataManager.getMatchDetail(matchId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayMatch(it.events[0])
                            view?.hideLoading()
                            checkIfMatchIsFavorite(it.events[0])
                        }, {
                            view?.hideLoading()
                        })
        )
    }

    override fun checkIfMatchIsFavorite(match: Match) {
        disposable.add(
                dataManager.isFavorite(match.matchId.toString())
                        .subscribe({
                            view?.displayFavoriteStatus(it)
                        }, {

                        })
        )
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