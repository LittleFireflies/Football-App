package com.littlefireflies.footballclub.presentation.ui.matchdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.bumptech.glide.Glide
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.utils.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.item_home_list.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MatchDetailActivity : BaseActivity(), MatchDetailContract.View {

    companion object {
        const val EXTRA_TEAM_ID = "matchId"
        private const val HOME_STRING = "home"
        private const val AWAY_STRING = "away"
    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var match: Match

    @Inject
    lateinit var presenter: MatchDetailPresenter<MatchDetailContract.View>

    override fun getLayoutId(): Int = R.layout.activity_match_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityComponent.inject(this)
        onAttachView()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        presenter.getMatchDetail(intent.getStringExtra("matchId"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) presenter.removeFromFavorite(match) else presenter.addToFavorite(match)

                isFavorite = !isFavorite
                displayFavoriteStatus(isFavorite)

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
        pbMatchDetail.show()
    }

    override fun hideLoading() {
        pbMatchDetail.hide()
    }

    override fun displayMatch(match: Match) {
        this.match = match

        val date = dateFormatter(match.matchDate)
        val time = timeFormatter(match.matchTime)

        presenter.getHomeTeamImage(match.homeTeamId)
        presenter.getAwayTeamImage(match.awayTeamId)

        tvDateTime.text = toGmtFormat("$date $time")
        tvHomeTeam.text = match.homeTeam
        tvAwayTeam.text = match.awayTeam
        tvHomeScore.text = match.homeScore
        tvAwayScore.text = match.awayScore
        tvHomeFormation.text = match.homeFormation
        tvAwayFormation.text = match.awayFormation
        rvHomeGoals.adapter = MatchDetailAdapter(match.homeGoals?.split(";"), HOME_STRING)
        rvHomeGoals.layoutManager = LinearLayoutManager(this)
        rvAwayGoals.adapter = MatchDetailAdapter(match.awayGoals?.split(";"), AWAY_STRING)
        rvAwayGoals.layoutManager = LinearLayoutManager(this)
        rvHomeRed.adapter = MatchDetailAdapter(match.homeRedCards?.split(";"), HOME_STRING)
        rvHomeRed.layoutManager = LinearLayoutManager(this)
        rvAwayRed.adapter = MatchDetailAdapter(match.awayRedCards?.split(";"), AWAY_STRING)
        rvAwayRed.layoutManager = LinearLayoutManager(this)
        rvHomeYellow.adapter = MatchDetailAdapter(match.homeYellowCards?.split(";"), HOME_STRING)
        rvHomeYellow.layoutManager = LinearLayoutManager(this)
        rvAwayYellow.adapter = MatchDetailAdapter(match.awayYellowCards?.split(";"), AWAY_STRING)
        rvAwayYellow.layoutManager = LinearLayoutManager(this)
        rvHomeGk.adapter = MatchDetailAdapter(match.homeGK?.split(";"), HOME_STRING)
        rvHomeGk.layoutManager = LinearLayoutManager(this)
        rvAwayGk.adapter = MatchDetailAdapter(match.awayGK?.split(";"), AWAY_STRING)
        rvAwayGk.layoutManager = LinearLayoutManager(this)
        rvHomeDef.adapter = MatchDetailAdapter(match.homeDefense?.split(";"), HOME_STRING)
        rvHomeDef.layoutManager = LinearLayoutManager(this)
        rvAwayDef.adapter = MatchDetailAdapter(match.awayDefense?.split(";"), AWAY_STRING)
        rvAwayDef.layoutManager = LinearLayoutManager(this)
        rvHomeMid.adapter = MatchDetailAdapter(match.homeMidfield?.split(";"), HOME_STRING)
        rvHomeMid.layoutManager = LinearLayoutManager(this)
        rvAwayMid.adapter = MatchDetailAdapter(match.awayMidfield?.split(";"), AWAY_STRING)
        rvAwayMid.layoutManager = LinearLayoutManager(this)
        rvHomeFwd.adapter = MatchDetailAdapter(match.homeForward?.split(";"), HOME_STRING)
        rvHomeFwd.layoutManager = LinearLayoutManager(this)
        rvAwayFwd.adapter = MatchDetailAdapter(match.awayForward?.split(";"), AWAY_STRING)
        rvAwayFwd.layoutManager = LinearLayoutManager(this)
        rvHomeSub.adapter = MatchDetailAdapter(match.homeSubs?.split(";"), HOME_STRING)
        rvHomeSub.layoutManager = LinearLayoutManager(this)
        rvAwaySub.adapter = MatchDetailAdapter(match.awaySubs?.split(";"), AWAY_STRING)
        rvAwaySub.layoutManager = LinearLayoutManager(this)

        ivHomeTeam.setOnClickListener {
            startActivity<TeamDetailActivity>(TeamDetailActivity.EXTRA_TEAM to match.homeTeamId)
        }
        ivAwayTeam.setOnClickListener {
            startActivity<TeamDetailActivity>(TeamDetailActivity.EXTRA_TEAM to match.awayTeamId)
        }
    }

    override fun displayErrorMessages(message: String) {
        snackbar(pbMatchDetail, message)
    }

    override fun displayHomeBadge(teamBadge: String?) {
        Glide.with(this).load(teamBadge).into(ivHomeTeam)
    }

    override fun displayAwayBadge(teamBadge: String?) {
        Glide.with(this).load(teamBadge).into(ivAwayTeam)
    }

    override fun displayFavoriteStatus(favorite: Boolean) {
        isFavorite = favorite
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorites)
    }

    override fun onAddtoFavorite() {
        snackbar(pbMatchDetail, "Added to favorite")
    }

    override fun onRemoveFromFavorite() {
        snackbar(pbMatchDetail, "Removed from favorite")
    }

    class MatchDetailAdapter(val items: List<String>?, val type: String) : RecyclerView.Adapter<MatchDetailAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                1 -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_list, parent, false))
                else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_away_list, parent, false))
            }
        }

        override fun getItemCount(): Int = if (items != null) items.size else 0

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(items?.get(position))
        }

        override fun getItemViewType(position: Int): Int {
            return when (type) {
                "home" -> 1
                else -> 0
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(item: String?) {
                itemView.tvItem.text = item?.trim()
            }
        }
    }
}
