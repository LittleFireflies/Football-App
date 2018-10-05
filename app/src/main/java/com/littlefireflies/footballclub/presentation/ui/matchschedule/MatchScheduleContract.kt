package com.littlefireflies.footballclub.presentation.ui.matchschedule

import com.littlefireflies.footballclub.presentation.base.BaseView
import com.littlefireflies.footballclub.presentation.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface MatchScheduleContract {
    interface View: BaseView {

    }

    interface UserActionListener<V: View>: IBasePresenter<V> {

    }
}