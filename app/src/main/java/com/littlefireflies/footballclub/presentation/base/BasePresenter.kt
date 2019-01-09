package com.littlefireflies.footballclub.presentation.base

import com.littlefireflies.footballclub.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
open class BasePresenter<V : BaseView>
constructor(val disposable: CompositeDisposable, val schedulerProvider: SchedulerProvider) : IBasePresenter<V> {
    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
        disposable.dispose()
    }

}
