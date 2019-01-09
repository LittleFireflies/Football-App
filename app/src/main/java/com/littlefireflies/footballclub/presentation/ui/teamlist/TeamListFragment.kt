package com.littlefireflies.footballclub.presentation.ui.teamlist


import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.bumptech.glide.Glide
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_team_list.*
import kotlinx.android.synthetic.main.item_team_list.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.inject

class TeamListFragment : BaseFragment(), TeamListContract.View {

    val presenter: TeamListPresenter<TeamListContract.View> by inject()

    override var selectedLeague: League
        get() = spTeamList.selectedItem as League
        set(value) {}

    override fun getLayoutId(): Int = R.layout.fragment_team_list

    override fun onLoadFragment(saveInstance: Bundle?) {
        onAttachView()
        setHasOptionsMenu(true)

        spTeamList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedLeague = parent?.getItemAtPosition(position) as League
                presenter.getTeamList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        presenter.getLeagueList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        searchView.queryHint = "Search team..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    presenter.getTeamList()
                    spTeamList.show()
                } else {
                    presenter.searchTeam(query.toString())
                    spTeamList.hide()
                }
                return true
            }
        })
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
        pbTeamList.snackbar(message)
    }

    inner class TeamAdapter(private val teams: List<Team>, val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false))

        override fun getItemCount(): Int = teams.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(teams[position])
        }

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(team: Team) {
                Glide.with(requireContext()).load(team.teamBadge).into(itemView.ivBadge)
                itemView.tvTeamName.text = team.teamName
                itemView.setOnClickListener { listener(team) }
            }
        }
    }

}
