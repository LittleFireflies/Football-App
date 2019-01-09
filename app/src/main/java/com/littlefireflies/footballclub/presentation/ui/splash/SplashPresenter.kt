package com.littlefireflies.footballclub.presentation.ui.splash

import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
class SplashPresenter<V : SplashContract.View>
constructor(private val leagueRepository: LeagueRepository, disposable: CompositeDisposable, schedulerProvider: SchedulerProvider) : BasePresenter<V>(disposable, schedulerProvider), SplashContract.UserActionListener<V> {

    override fun getLeagueList() {
        disposable.add(
                leagueRepository.getSoccerLeagueList()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            leagueRepository.saveLeagueList(it)
                            view?.openActivity()
                        }, {
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}