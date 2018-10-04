package com.littlefireflies.footballclub.presentation.teamdetail.overview


import android.os.Bundle

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_team_overview.*

class TeamOverviewFragment : BaseFragment(), TeamDetailActivity.DataListener {

    override fun getLayoutId(): Int = R.layout.fragment_team_overview

    override fun onLoadFragment(saveInstance: Bundle?) {
        val teamActivity = activity as TeamDetailActivity
        teamActivity.setTeamDataListener(this)
    }

    override fun onDataRceived(team: Team) {
        tvOverview.text = team.teamOverview
    }

}
