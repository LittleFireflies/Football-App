package com.littlefireflies.footballclub.presentation.matchschedule

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.favoritematch.FavoriteMatchFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_match_schedule.*
import javax.inject.Inject

class MatchScheduleActivity : BaseActivity(), MatchScheduleContract.View {

    @Inject
    lateinit var presenter: MatchScheduleContract.UserActionListener<MatchScheduleContract.View>

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
                R.id.action_prev -> viewPager.setCurrentItem(0)
                R.id.action_next -> viewPager.setCurrentItem(1)
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
        adapter.addFragment(PreviousMatchFragment(), "Prev. Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        adapter.addFragment(FavoriteMatchFragment(), "Favorite Match")
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
