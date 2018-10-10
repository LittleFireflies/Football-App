package com.littlefireflies.footballclub.presentation.ui.teamlist


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_team_list.*
import kotlinx.android.synthetic.main.item_team_list.*
import kotlinx.android.synthetic.main.item_team_list.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TeamListFragment : BaseFragment(), TeamListContract.View {

    @Inject
    lateinit var presenter: TeamListPresenter<TeamListContract.View>

    override var selectedLeague: League
        get() = spTeamList.selectedItem as League
        set(value) {}

    override fun getLayoutId(): Int = R.layout.fragment_team_list

    override fun onLoadFragment(saveInstance: Bundle?) {
        if (activityComponent != null) {
            activityComponent?.inject(this)
            onAttachView()

            spTeamList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedLeague = parent?.getItemAtPosition(position) as League
                    presenter.getTeamList()
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
        pbTeamList.show()
    }

    override fun hideLoading() {
        pbTeamList.hide()
    }

    override fun displayLeagueList(leagues: List<League>) {
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, leagues)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTeamList.adapter = spinnerAdapter
    }

    override fun displayTeamList(teams: List<Team>) {
        rvTeamList.adapter = TeamAdapter((teams)) {
            startActivity<TeamDetailActivity>(TeamDetailActivity.EXTRA_TEAM to it.teamId)
        }
        rvTeamList.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessage(message: String) {
        snackbar(pbTeamList, message)
    }

    inner class TeamAdapter(val teams: List<Team>, val listener : (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false))

        override fun getItemCount(): Int = teams.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(teams[position])
        }

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(team: Team) {
                Glide.with(context!!).load(team.teamBadge).into(itemView.ivBadge)
                itemView.tvTeamName.text = team.teamName
                itemView.setOnClickListener { listener(team) }
            }
        }
    }

}
