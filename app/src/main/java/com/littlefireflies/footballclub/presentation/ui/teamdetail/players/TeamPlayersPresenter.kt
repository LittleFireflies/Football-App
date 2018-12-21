package com.littlefireflies.footballclub.presentation.ui.teamdetail.players

import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class TeamPlayersPresenter<V: TeamPlayersContract.View> @Inject
constructor(private val playerRepository: PlayerRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), TeamPlayersContract.UserActionListener<V> {

    override fun getPlayerList(teamId: String?) {
        view?.showLoading()
        disposable.add(
                playerRepository.getPlayers(teamId.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayPlayerList(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}