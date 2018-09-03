package com.littlefireflies.footballclub.ui.MatchSchedule

import com.littlefireflies.footballclub.ui.base.BaseView
import com.littlefireflies.footballclub.ui.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface MatchScheduleContract {
    interface View: BaseView {

    }

    interface UserActionListener<V: View>: IBasePresenter<V> {

    }
}