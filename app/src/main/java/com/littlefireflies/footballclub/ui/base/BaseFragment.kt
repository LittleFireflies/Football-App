package com.littlefireflies.footballclub.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import com.littlefireflies.footballclub.di.component.ActivityComponent

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
abstract class BaseFragment : Fragment() {

    private var activity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.activity = activity
        }
    }

    val activityComponent: ActivityComponent? = activity?.activityComponent
}
