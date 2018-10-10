package com.littlefireflies.footballclub.presentation.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.ui.match.MatchFragment
import com.littlefireflies.footballclub.presentation.ui.teamlist.TeamListFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_match_schedule.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.UserActionListener<MainContract.View>

    override fun onActivityReady(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        onAttachView()

        setupViewPager(viewPager)
        bottomNavListener()
        viewPagerListener()
    }

    override fun getLayoutId(): Int = R.layout.activity_match_schedule

    fun bottomNavListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_match -> viewPager.setCurrentItem(0)
                R.id.action_team -> viewPager.setCurrentItem(1)
                R.id.action_favorite -> viewPager.setCurrentItem(2)
            }

            false
        }
    }

    fun viewPagerListener() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).setChecked(true)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MatchFragment(), "Match")
        adapter.addFragment(TeamListFragment(), "Team")
        adapter.addFragment(FavoriteMatchFragment(), "Favorite")
        viewPager.adapter = adapter
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
