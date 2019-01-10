package com.littlefireflies.footballclub.presentation.base


/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
open class BasePresenter<V : BaseView> : IBasePresenter<V> {
    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

}
