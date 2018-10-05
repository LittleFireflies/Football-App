package com.littlefireflies.footballclub.presentation.ui.favoritematch

import com.littlefireflies.footballclub.domain.favoritematch.GetFavoriteMatchUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
class FavoriteMatchPresenter<V: FavoriteMatchContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), FavoriteMatchContract.UserActionListener<V> {

    @Inject
    lateinit var getFavoriteMatchUseCase: GetFavoriteMatchUseCase

    override fun getFavoriteMatchList() {
        view?.showLoading()
        disposable.add(
                getFavoriteMatchUseCase.getFavoriteMatchList()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayFavoriteMatchList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessages("Unable to load Favorite matches")
                        })
        )
    }
}