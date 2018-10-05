package com.littlefireflies.footballclub.presentation.ui.teamdetail

import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDetailPresenter<V: TeamDetailContract.View> @Inject
constructor(disposable: CompositeDisposable, schedulerProvider: SchedulerProvider): BasePresenter<V>(disposable, schedulerProvider), TeamDetailContract.UserActionListener<V>{

    @Inject
    lateinit var teamDetailUseCase: TeamDetailUseCase

    override fun getTeamDetail(teamId: String) {
        view?.showLoading()
        disposable.add(
                teamDetailUseCase.getTeamDetail(teamId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            view?.displayTeam(it)
                            view?.hideLoading()
                        }, {
                            view?.hideLoading()
                            view?.displayErrorMessage("Unable to load the data")
                        })
        )
    }
}