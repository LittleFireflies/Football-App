package com.littlefireflies.footballclub.presentation.ui.favorite


import android.os.Bundle
import android.support.v4.view.ViewPager

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.presentation.ui.favoriteteam.FavoriteTeamFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_favorite

    override fun onLoadFragment(saveInstance: Bundle?) {
        setupViewPager(viewPagerFavorite)
        tabLayoutFavorite.setupWithViewPager(viewPagerFavorite)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteMatchFragment(), "Matches")
        adapter.addFragment(FavoriteTeamFragment(), "Teams")
        viewPager.adapter = adapter
    }

}
