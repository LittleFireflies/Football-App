package com.littlefireflies.footballclub.ui.MatchDetail

import com.littlefireflies.footballclub.ui.base.BaseView
import com.littlefireflies.footballclub.ui.base.IBasePresenter

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
interface MatchDetailContract {

    interface View: BaseView {

    }

    interface UserActionListener<V: View>: IBasePresenter<V> {

    }
}