package com.littlefireflies.footballclub.ui.MatchSchedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch.NextMatchFragment
import com.littlefireflies.footballclub.ui.MatchSchedule.PreviousMatch.PreviousMatchFragment
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_match_schedule.*
import org.jetbrains.anko.support.v4.onPageChangeListener

class MatchScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_schedule)

        setupViewPager(viewPager)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_prev -> viewPager.setCurrentItem(0)
                R.id.action_next -> viewPager.setCurrentItem(1)
            }

            false
        }

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
        viewPager.adapter = adapter
    }

}
