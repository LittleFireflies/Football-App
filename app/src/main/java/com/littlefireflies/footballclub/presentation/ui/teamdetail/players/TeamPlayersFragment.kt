package com.littlefireflies.footballclub.presentation.ui.teamdetail.players

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.playerdetail.PlayerDetailActivity
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_team_players.*
import kotlinx.android.synthetic.main.item_player_list.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TeamPlayersFragment : BaseFragment(), TeamPlayersContract.View, TeamDetailActivity.DataListener {

    @Inject
    lateinit var presenter: TeamPlayersPresenter<TeamPlayersContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_team_players

    override fun onLoadFragment(saveInstance: Bundle?) {
        val component = activityComponent
        if (component != null) {
            activityComponent?.inject(this)
            onAttachView()
            val teamActivity = activity as TeamDetailActivity
            teamActivity.setPlayerDataListener(this)
        }
    }

    override fun onDestroyView() {
        onDetachView()
        super.onDestroyView()
    }

    override fun onDataRceived(team: Team) {
        presenter.getPlayerList(team.teamId)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun showLoading() {
        pbTeamPlayers.show()
    }

    override fun hideLoading() {
        pbTeamPlayers.hide()
    }

    override fun displayPlayerList(players: List<Player>) {
        rvPlayerlist.adapter = PlayerAdapter(context, players) {
            startActivity<PlayerDetailActivity>("playerId" to it.playerId)
        }
        rvPlayerlist.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessage(message: String) {
        snackbar(pbTeamPlayers, message)
    }

    inner class PlayerAdapter(val context: Context?, val players: List<Player>, val listener: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player_list, parent, false))

        override fun getItemCount(): Int = players.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(players[position])
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(player: Player) {
                Glide.with(context!!).load(player.imageCutout).into(itemView.ivPicture)
                itemView.tvPlayerName.text = player.playerName
                itemView.tvPlayerPosition.text = player.position
                itemView.setOnClickListener { listener(player) }
            }
        }
    }
}
