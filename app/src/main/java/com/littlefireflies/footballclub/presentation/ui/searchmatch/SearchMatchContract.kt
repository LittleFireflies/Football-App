package com.littlefireflies.footballclub.presentation.ui.searchmatch

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by Widyarso Joko Purnomo on 13/10/18
 */
interface SearchMatchContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayMatch(matchList: List<Match>)
        fun displayErrorMessage(message: String)
    }

    interface UserActionListener<V: View> : IBasePresenter<V> {
        fun searchMatch(matchName: String)
    }
}