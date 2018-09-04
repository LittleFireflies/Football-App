package com.littlefireflies.footballclub.ui.MatchDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.ui.base.BaseActivity
import javax.inject.Inject

class MatchDetailActivity : BaseActivity(), MatchDetailContract.View {

    @Inject
    lateinit var presenter: MatchDetailPresenter<MatchDetailContract.View>

    override fun getLayoutId(): Int = R.layout.activity_match_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        onAttachView()
    }

    override fun onResume() {
        super.onResume()

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
}
