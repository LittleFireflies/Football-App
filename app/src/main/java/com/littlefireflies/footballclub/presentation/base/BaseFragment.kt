package com.littlefireflies.footballclub.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
abstract class BaseFragment : Fragment() {

    private var activity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.activity = context
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun onLoadFragment(saveInstance: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onLoadFragment(savedInstanceState)
    }

}
