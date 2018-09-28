package com.littlefireflies.footballclub.presentation.favoritematch

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
interface FavoriteMatchContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun displayFavoriteMatchList(matchList: List<FavoriteMatch>)
        fun displayErrorMessages(message: String)
    }

    interface UserActionListener<V: View>: IBasePresenter<V> {
        fun getFavoriteMatchList()
    }
}