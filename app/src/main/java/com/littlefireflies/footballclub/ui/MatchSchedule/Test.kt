package com.littlefireflies.footballclub.ui.MatchSchedule

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class Test : AppCompatActivity() {

    internal var viewPager: ViewPager? = null
    internal var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView!!.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}
