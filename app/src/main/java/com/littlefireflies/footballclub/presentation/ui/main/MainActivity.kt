package com.littlefireflies.footballclub.presentation.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.favorite.FavoriteFragment
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.ui.match.MatchFragment
import com.littlefireflies.footballclub.presentation.ui.teamlist.TeamListFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_match_schedule.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    override fun onActivityReady(savedInstanceState: Bundle?) {
        activityComponent.inject(this)

        setupViewPager(viewPager)
        bottomNavListener()
    }

    override fun getLayoutId(): Int = R.layout.activity_match_schedule

    fun bottomNavListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_match -> viewPager.currentItem = 0
                R.id.action_team -> viewPager.currentItem = 1
                R.id.action_favorite -> viewPager.currentItem = 2
            }

            true
        }
    }

    fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MatchFragment(), "Match")
        adapter.addFragment(TeamListFragment(), "Team")
        adapter.addFragment(FavoriteFragment(), "Favorite")
        viewPager.adapter = adapter
    }

}
