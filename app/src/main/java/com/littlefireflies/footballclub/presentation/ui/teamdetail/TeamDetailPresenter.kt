package com.littlefireflies.footballclub.presentation.ui.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.domain.favoriteteam.AddFavoriteTeamUseCase
import com.littlefireflies.footballclub.domain.favoriteteam.GetFavoriteTeamUseCase
import com.littlefireflies.footballclub.domain.favoriteteam.RemoveFavoriteTeamUseCase
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDetailPresenter<V : TeamDetailContract.View> @Inject
constructor(
        private val teamDetailUseCase: TeamDetailUseCase,
        private val getFavoriteTeamUseCase: GetFavoriteTeamUseCase,
        private val addFavoriteTeamUseCase: AddFavoriteTeamUseCase,
        private val removeFavoriteTeamUseCase: RemoveFavoriteTeamUseCase,
        disposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider
) : BasePresenter<V>(disposable, schedulerProvider), TeamDetailContract.UserActionListener<V> {

    override fun getTeamDetail(teamId: String) {
        view?.showLoading()
        disposable.add(
                teamDetailUseCase.getTeamDetail(teamId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .doOnSuccess {
                            view?.displayTeam(it)
                        }
                        .doOnError {
                            view?.displayErrorMessage("Unable to load the data")
                        }
                        .flatMap {
                            getFavoriteTeamUseCase.getFavoriteTeamStatus(it.teamId.toString())
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
        addFavoriteTeamUseCase.addToFavorite(team)
        view?.onAddToFavorite()
    }

    override fun removeFromFavorite(team: Team) {
        removeFavoriteTeamUseCase.removeFavoriteTeam(team.teamId.toString())
        view?.onRemoveFromFavorite()
    }
}