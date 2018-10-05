package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
interface PreviousMatchContract {

    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayMatchList(events: List<Match>)
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View>: IBasePresenter<V> {
        fun getMatchList()
    }
}