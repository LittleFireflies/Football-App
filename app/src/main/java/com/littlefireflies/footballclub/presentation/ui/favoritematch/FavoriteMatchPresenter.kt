package com.littlefireflies.footballclub.presentation.ui.favoritematch

import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
class FavoriteMatchPresenter<V: FavoriteMatchContract.View> @Inject
constructor(private val matchRepository: MatchRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), FavoriteMatchContract.UserActionListener<V> {

    override fun getFavoriteMatchList() {
        view?.showLoading()
        disposable.add(
                matchRepository.getFavoriteMatches()
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