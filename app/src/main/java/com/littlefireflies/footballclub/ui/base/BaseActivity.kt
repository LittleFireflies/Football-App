package com.littlefireflies.footballclub.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.littlefireflies.footballclub.App
import com.littlefireflies.footballclub.di.component.ActivityComponent
import com.littlefireflies.footballclub.di.component.DaggerActivityComponent
import com.littlefireflies.footballclub.di.module.ActivityModule

/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as App).applicationComponent)
                .build()
    }
}
