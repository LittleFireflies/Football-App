package com.littlefireflies.footballclub.presentation.ui.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDetailPresenter<V : TeamDetailContract.View>
constructor(
        private val teamRepository: TeamRepository,
        disposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider
) : BasePresenter<V>(disposable, schedulerProvider), TeamDetailContract.UserActionListener<V> {

    override fun getTeamDetail(teamId: String) {
        view?.showLoading()
        disposable.add(
                teamRepository.getTeamDetail(teamId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .doOnSuccess {
                            view?.displayTeam(it)
                        }
                        .doOnError {
                            view?.displayErrorMessage("Unable to load the data")
                        }
                        .flatMap {
                            teamRepository.isFavorite(it.teamId.toString())
                        }
                        .subscribe({
                            view?.displayFavoriteStatus(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
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