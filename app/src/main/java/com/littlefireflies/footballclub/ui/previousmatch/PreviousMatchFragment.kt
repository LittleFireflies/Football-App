package com.littlefireflies.footballclub.ui.previousmatch


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.ui.base.BaseFragment
import com.littlefireflies.footballclub.utils.dateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_previous_match.*
import kotlinx.android.synthetic.main.item_prev_match.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class PreviousMatchFragment : BaseFragment(), PreviousMatchContract.View {

    @Inject
    lateinit var presenter: PreviousMatchPresenter<PreviousMatchContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_previous_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        val component = activityComponent
        if (component !=  null) {
            activityComponent?.inject(this)
            onAttachView()
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
        pbPrevMatch.show()
    }

    override fun hideLoading() {
        pbPrevMatch.hide()
    }

    override fun displayMatchList(events: List<Match>) {
        val adapter = PreviousMatchAdapter(events, object : ActionListener {
            override fun onClick(match: Match) {
                startActivity<MatchDetailActivity>("matchId" to "${match.matchId}")
            }
        })
        rvPrevMatch.adapter = adapter
        rvPrevMatch.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessage(message: String) {
        snackbar(rvPrevMatch, message)
    }

    class PreviousMatchAdapter(val matches: List<Match>, val listener: ActionListener): RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {
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

                itemView.setOnClickListener { listener.onClick(match) }
            }
        }

    }

    interface ActionListener {
        fun onClick(match: Match)
    }
}
