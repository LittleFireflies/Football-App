package com.littlefireflies.footballclub.presentation.ui.playerdetail

import com.littlefireflies.footballclub.domain.playerdetail.PlayerDetailUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
class PlayerDetailPresenter<V: PlayerDetailContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), PlayerDetailContract.UserActionListener<V>{

    @Inject
    lateinit var playerDetailUseCase: PlayerDetailUseCase

    override fun getPlayerDetail(playerId: String) {
        view?.showLoading()
        disposable.add(
                playerDetailUseCase.getPlayerDetail(playerId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayPlayer(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}