package com.littlefireflies.footballclub.presentation.teamdetail

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.presentation.teamdetail.overview.TeamOverviewFragment
import com.littlefireflies.footballclub.presentation.teamdetail.players.TeamPlayersFragment
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class TeamDetailActivity : BaseActivity(), TeamDetailContract.View {

    @Inject
    lateinit var presenter: TeamDetailPresenter<TeamDetailContract.View>

    var team: Team? = null

    companion object {
        const val EXTRA_TEAM = "teamId"
    }

    override fun getLayoutId(): Int = R.layout.activity_team_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityComponent.inject(this)
        onAttachView()
        setupViewPager(viewPagerTeam)
        tabLayoutTeam.setupWithViewPager(viewPagerTeam)
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        presenter.getTeamDetail(intent.getStringExtra(EXTRA_TEAM))
    }

    fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TeamOverviewFragment(), "OVERVIEW")
        adapter.addFragment(TeamPlayersFragment(), "PLAYERS")
        viewPager.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
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
        pbTeamDetail.show()
    }

    override fun hideLoading() {
        pbTeamDetail.hide()
    }

    override fun displayTeam(team: Team) {
        setTitle("")
        tvTeamName.text = team.teamName
        tvFormedYear.text = team.teamFormedYear
        tvStadium.text = team.teamStadium
        Glide.with(this).load(team.teamBadge).into(ivTeamBadge)
        teamDataListener?.onDataRceived(team)
        playerDataListener?.onDataRceived(team)
    }

    override fun displayErrorMessage(message: String) {
        snackbar(pbTeamDetail, message)
    }

    private var teamDataListener: DataListener? = null
    private var playerDataListener: DataListener? = null

    interface DataListener {
        fun onDataRceived(team: Team)
    }

    fun setTeamDataListener(listener: DataListener) {
        teamDataListener = listener
    }

    fun setPlayerDataListener(listener: DataListener) {
        playerDataListener = listener
    }
}
