package com.littlefireflies.footballclub.presentation.base

/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
interface IBasePresenter<V : BaseView> {

    fun onAttach(view: V)

    fun onDetach()

}
