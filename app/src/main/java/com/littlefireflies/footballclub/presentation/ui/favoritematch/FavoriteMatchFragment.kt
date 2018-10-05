package com.littlefireflies.footballclub.presentation.ui.favoritematch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailActivity
import com.littlefireflies.footballclub.utils.dateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import kotlinx.android.synthetic.main.item_prev_match.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : BaseFragment(), FavoriteMatchContract.View {

    @Inject
    lateinit var presenter: FavoriteMatchPresenter<FavoriteMatchContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_favorite_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        if (activityComponent != null) {
            activityComponent?.inject(this)
            onAttachView()

            swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(context!!, android.R.color.holo_blue_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_green_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_orange_light),
                    ContextCompat.getColor(context!!, android.R.color.holo_red_light)
            )
            swipeRefreshLayout.onRefresh {
                presenter.getFavoriteMatchList()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMatchList()
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
        pbFavorite.show()
    }

    override fun hideLoading() {
        pbFavorite.hide()
    }

    override fun displayFavoriteMatchList(matchList: List<FavoriteMatch>) {
        swipeRefreshLayout.isRefreshing = false

        val adapter = FavoriteMatchAdapter(matchList) {
            startActivity<MatchDetailActivity>("matchId" to "${it.matchId}")
        }

        rvFavorite.adapter = adapter
        rvFavorite.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessages(message: String) {
        snackbar(rvFavorite, message)
    }

    internal class FavoriteMatchAdapter(val matches: List<FavoriteMatch>, val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prev_match, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(matches[position])
        }

        override fun getItemCount(): Int = matches.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItem(match: FavoriteMatch) {
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
