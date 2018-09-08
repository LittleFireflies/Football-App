package com.littlefireflies.footballclub.ui.favoritematch

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.ui.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
class FavoriteMatchPresenter<V: FavoriteMatchContract.View> @Inject
constructor(dataManager: DataManager, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(dataManager, disposable, schedulerProvider), FavoriteMatchContract.UserActionListener<V> {

    override fun loadFavoriteMatchList() {
        view?.showLoading()
        disposable.add(
                dataManager.getFavoriteMatches()
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