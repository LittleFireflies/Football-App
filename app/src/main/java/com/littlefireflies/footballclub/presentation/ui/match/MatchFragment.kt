package com.littlefireflies.footballclub.presentation.ui.match


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchFragment
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchFragment
import com.littlefireflies.footballclub.presentation.ui.searchmatch.SearchMatchActivity
import com.littlefireflies.footballclub.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        setupViewPager(viewPagerMatch)
        tabLayoutMatch.setupWithViewPager(viewPagerMatch)
        setHasOptionsMenu(true)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PreviousMatchFragment(), "Last")
        adapter.addFragment(NextMatchFragment(), "Next")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search_match, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search -> {
                startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
