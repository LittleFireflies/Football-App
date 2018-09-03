package com.littlefireflies.footballclub.ui.base


import com.littlefireflies.footballclub.data.DataManager
import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
open class BasePresenter<V : BaseView> @Inject
constructor(val dataManager: DataManager, val disposable: CompositeDisposable) : IBasePresenter<V> {
    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
        disposable.dispose()
    }

}
