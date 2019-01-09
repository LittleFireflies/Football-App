package com.littlefireflies.footballclub.presentation.ui.playerdetail

import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
class PlayerDetailPresenter<V : PlayerDetailContract.View>
constructor(private val playerRepository: PlayerRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), PlayerDetailContract.UserActionListener<V> {

    override fun getPlayerDetail(playerId: String) {
        view?.showLoading()
        disposable.add(
                playerRepository.getPlayerDetail(playerId)
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