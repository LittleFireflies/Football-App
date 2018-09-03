package com.littlefireflies.footballclub.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.littlefireflies.footballclub.di.component.ActivityComponent

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
abstract class BaseFragment : Fragment() {

    private var activity: BaseActivity? = null
    var contentView: View? = null

    override fun getView(): View? {
        return super.getView()
    }

    abstract fun getLayoutId(): Int

    protected abstract fun onLoadFragment(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.let {
            contentView = inflater.inflate(getLayoutId(), container, false)
        }

        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onLoadFragment(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    val activityComponent: ActivityComponent? = activity?.activityComponent
}
