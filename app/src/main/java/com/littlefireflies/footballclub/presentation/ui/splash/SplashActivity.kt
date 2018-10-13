package com.littlefireflies.footballclub.presentation.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter<SplashContract.View>

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onActivityReady(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
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
        snackbar(pbSplash, message)
    }
}
