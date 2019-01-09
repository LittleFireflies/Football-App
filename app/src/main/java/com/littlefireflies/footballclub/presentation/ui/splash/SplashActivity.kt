package com.littlefireflies.footballclub.presentation.ui.splash

import android.os.Bundle
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity(), SplashContract.View {

    val presenter: SplashPresenter<SplashContract.View> by inject()

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onActivityReady(savedInstanceState: Bundle?) {
        onAttachView()
        presenter.getLeagueList()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun openActivity() {
        startActivity<MainActivity>()
        finish()
    }

    override fun displayErrorMessage(message: String) {
        pbSplash.snackbar(message)
    }
}
