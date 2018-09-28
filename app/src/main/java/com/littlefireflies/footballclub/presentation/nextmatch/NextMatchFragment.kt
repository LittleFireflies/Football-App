package com.littlefireflies.footballclub.presentation.nextmatch


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.utils.dateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.item_next_match.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class NextMatchFragment : BaseFragment(), NextMatchContract.View {

    @Inject
    lateinit var presenter: NextMatchPresenter<NextMatchContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_next_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        val component = activityComponent
        if (component != null) {
            activityComponent?.inject(this)
            onAttachView()

            swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(context!!, android.R.color.holo_blue_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_green_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_orange_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_red_light)
            )
            swipeRefreshLayout.onRefresh {
                presenter.getMatchList()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getMatchList()
    }

    override fun onDestroyView() {
        onDetachView()
        super.onDestroyView()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun showLoading() {
        pbNextMatch?.show()
    }

    override fun hideLoading() {
        pbNextMatch?.hide()
    }

    override fun displayMatchList(events: List<Match>) {
        swipeRefreshLayout.isRefreshing = false

        val adapter = NextMatchAdapter(events) {
            startActivity<MatchDetailActivity>("matchId" to "${it.matchId}")
        }
        rvNextMatch.adapter = adapter
        rvNextMatch.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    override fun displayErrorMessage(message: String) {
        snackbar(rvNextMatch, message)
    }

    internal class NextMatchAdapter(val matches: List<Match>, val listener: (Match) -> Unit): RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false))

        override fun getItemCount(): Int = matches.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(matches[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            fun bindItem(match: Match) {
                val date = dateFormatter(match.matchDate)
                val time = match.matchTime?.split(":")

                itemView.tvHomeTeam.text = match.homeTeam
                itemView.tvAwayTeam.text = match.awayTeam
                itemView.tvDateTime.text = "$date ${time?.get(0)}:${time?.get(1)}"
                itemView.setOnClickListener { listener(match) }
            }
        }
    }

}
