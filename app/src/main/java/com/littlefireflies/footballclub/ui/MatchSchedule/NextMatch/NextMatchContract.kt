package com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch

import com.littlefireflies.footballclub.ui.base.BaseView
import com.littlefireflies.footballclub.ui.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NextMatchContract {

    interface View: BaseView {
    }

    interface UserActionListener<V: View>: IBasePresenter<V> {
        fun getMatchList()
    }
}