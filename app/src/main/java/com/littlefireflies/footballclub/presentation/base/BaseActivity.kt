package com.littlefireflies.footballclub.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by widyarso.purnomo on 03/08/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun onActivityReady(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onActivityReady(savedInstanceState)
    }
}
