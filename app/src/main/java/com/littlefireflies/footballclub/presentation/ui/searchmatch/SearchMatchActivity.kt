package com.littlefireflies.footballclub.presentation.ui.searchmatch

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.SearchView
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.utils.dateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import com.littlefireflies.footballclub.utils.timeFormatter
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.item_next_match.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchMatchActivity : BaseActivity(), SearchMatchContract.View {

    @Inject
    lateinit var presenter: SearchMatchPresenter<SearchMatchContract.View>

    override fun getLayoutId(): Int = R.layout.activity_search_match

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityComponent.inject(this)
        onAttachView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        searchView.queryHint = "Search match..."
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {

                } else {
                    presenter.searchMatch(query.toString())
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun showLoading() {
        pbSearchTeam.show()
    }

    override fun hideLoading() {
        pbSearchTeam.hide()
    }

    override fun displayMatch(matchList: List<Match>) {
        rvSearchMatch.adapter = SearchAdapter(matchList) {
            startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_TEAM_ID to it.matchId)
        }
        rvSearchMatch.layoutManager = LinearLayoutManager(this)
    }

    inner class SearchAdapter(val matchList: List<Match>, val listener : (Match) -> Unit) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false))

        override fun getItemCount(): Int = matchList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(matchList[position])
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(match: Match) {
                val date = dateFormatter(match.matchDate)
                val time = timeFormatter(match.matchTime)

                itemView.ivNotification.hide()
                itemView.tvDateTime.text = "$date $time"
                itemView.tvHomeTeam.text = match.homeTeam
                itemView.tvAwayTeam.text = match.awayTeam
                itemView.setOnClickListener { listener(match) }
            }
        }
    }

}
