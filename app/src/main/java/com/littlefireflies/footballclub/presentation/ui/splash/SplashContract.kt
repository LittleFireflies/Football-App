package com.littlefireflies.footballclub.presentation.ui.splash

import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
interface SplashContract {
    interface View : BaseView {
        fun openActivity()
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun getLeagueList()
    }
}