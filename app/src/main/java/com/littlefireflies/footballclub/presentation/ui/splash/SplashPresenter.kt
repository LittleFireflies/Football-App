package com.littlefireflies.footballclub.presentation.ui.splash

import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
class SplashPresenter<V: SplashContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), SplashContract.UserActionListener<V>{

    @Inject
    lateinit var leagueListUseCase: LeagueListUseCase

    override fun getLeagueList() {
        disposable.add(
                leagueListUseCase.getSoccerLeagueList()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            leagueListUseCase.saveSoccerLeagueList(it)
                            view?.openActivity()
                        }, {
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}