package com.littlefireflies.footballclub.presentation.ui.favoriteteam

import com.littlefireflies.footballclub.domain.favoriteteam.GetFavoriteTeamUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class FavoriteTeamPresenter<V : FavoriteTeamContract.View> @Inject
constructor(private val getFavoriteTeamUseCase: GetFavoriteTeamUseCase, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), FavoriteTeamContract.UserActionListener<V> {

    override fun getFavoriteTeamList() {
        view?.showLoading()
        disposable.add(
                getFavoriteTeamUseCase.getFavoriteTeamList()
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