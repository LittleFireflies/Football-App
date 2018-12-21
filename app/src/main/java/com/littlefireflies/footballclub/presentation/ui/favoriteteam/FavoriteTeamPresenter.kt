package com.littlefireflies.footballclub.presentation.ui.favoriteteam

import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class FavoriteTeamPresenter<V : FavoriteTeamContract.View> @Inject
constructor(private val teamRepository: TeamRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), FavoriteTeamContract.UserActionListener<V> {

    override fun getFavoriteTeamList() {
        view?.showLoading()
        disposable.add(
                teamRepository.getFavoriteTeamList()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayFavoriteTeamList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}