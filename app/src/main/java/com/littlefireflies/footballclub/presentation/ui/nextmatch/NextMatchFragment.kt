package com.littlefireflies.footballclub.presentation.ui.nextmatch


import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import com.littlefireflies.footballclub.utils.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.item_next_match.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import java.util.*
import javax.inject.Inject

class NextMatchFragment : BaseFragment(), NextMatchContract.View {

    @Inject
    lateinit var presenter: NextMatchPresenter<NextMatchContract.View>

    override var selectedLeague: League
        get() = spNextMatchList.selectedItem as League
        set(value) {}

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

            spNextMatchList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedLeague = parent?.getItemAtPosition(position) as League
                    presenter.getMatchList()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            presenter.getLeagueList()
        }
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

    override fun displayLeagueList(leagues: List<League>) {
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, leagues)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNextMatchList.adapter = spinnerAdapter
    }

    override fun displayMatchList(events: List<Match>) {
        swipeRefreshLayout.isRefreshing = false

        val adapter = NextMatchAdapter(events, {
            startActivity<MatchDetailActivity>("matchId" to "${it.matchId}")
        }, {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.setType("vnd.android.cursor.item/event")

            val date = dateFormatter(it.matchDate)
            val time = timeFormatter(it.matchTime)
            val startTime = getTimeMillis("$date $time")
            val endTime = startTime + 1000 * 60 * 60 * 2

            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            intent.putExtra(CalendarContract.Events.TITLE, it.matchName)

            startActivity(intent)
        })


        rvNextMatch.adapter = adapter
        rvNextMatch.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    override fun displayErrorMessage(message: String) {
        snackbar(rvNextMatch, message)
    }

    internal class NextMatchAdapter(val matches: List<Match>, val listener: (Match) -> Unit, val notificationListener: (Match) -> Unit) : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false))

        override fun getItemCount(): Int = matches.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(matches[position])
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItem(match: Match) {
                val date = dateFormatter(match.matchDate)
                val time = timeFormatter(match.matchTime)

                itemView.tvHomeTeam.text = match.homeTeam
                itemView.tvAwayTeam.text = match.awayTeam
                itemView.tvDateTime.text = "$date $time"
                itemView.ivNotification.setOnClickListener {notificationListener(match)}
                itemView.setOnClickListener { listener(match) }
            }
        }
    }

}
