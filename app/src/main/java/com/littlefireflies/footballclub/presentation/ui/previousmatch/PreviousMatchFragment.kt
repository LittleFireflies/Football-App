package com.littlefireflies.footballclub.presentation.ui.previousmatch


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.utils.dateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_previous_match.*
import kotlinx.android.synthetic.main.item_prev_match.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class PreviousMatchFragment : BaseFragment(), PreviousMatchContract.View {

    @Inject
    lateinit var presenter: PreviousMatchPresenter<PreviousMatchContract.View>

    override var selectedLeague: League
        get() = spPrevMatchList.selectedItem as League
        set(value) {}

    override fun getLayoutId(): Int = R.layout.fragment_previous_match

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

            spPrevMatchList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedLeague = parent?.getItemAtPosition(position) as League
                    presenter.getMatchList()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getLeagueList()
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
        pbPrevMatch.show()
    }

    override fun hideLoading() {
        pbPrevMatch.hide()
    }

    override fun displayLeagueList(leagues: List<League>) {
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, leagues)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPrevMatchList.adapter = spinnerAdapter
    }

    override fun displayMatchList(events: List<Match>) {
        swipeRefreshLayout.isRefreshing = false

        val adapter = PreviousMatchAdapter(events) {
            startActivity<MatchDetailActivity>("matchId" to "${it.matchId}")
        }
        rvPrevMatch.adapter = adapter
        rvPrevMatch.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessage(message: String) {
        snackbar(rvPrevMatch, message)
    }

    class PreviousMatchAdapter(val matches: List<Match>, val listener: (Match) -> Unit): RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prev_match, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(matches[position])
        }

        override fun getItemCount(): Int = matches.size

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            fun bindItem(match: Match) {
                val date = dateFormatter(match.matchDate)
                val time = match.matchTime?.split(":")

                itemView.tvHomeTeam.text = match.homeTeam
                itemView.tvAwayTeam.text = match.awayTeam
                itemView.tvDateTime.text = "$date ${time?.get(0)}:${time?.get(1)}"
                itemView.tvHomeScore.text = match.homeScore
                itemView.tvAwayScore.text = match.awayScore
                itemView.setOnClickListener { listener(match) }
            }
        }

    }
}
