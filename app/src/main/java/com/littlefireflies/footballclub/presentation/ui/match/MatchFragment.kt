package com.littlefireflies.footballclub.presentation.ui.match


import android.os.Bundle
import android.support.v4.view.ViewPager

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        setupViewPager(viewPagerMatch)
        tabLayoutMatch.setupWithViewPager(viewPagerMatch)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PreviousMatchFragment(), "Last")
        adapter.addFragment(NextMatchFragment(), "Next")
        viewPager.adapter = adapter
    }

}
