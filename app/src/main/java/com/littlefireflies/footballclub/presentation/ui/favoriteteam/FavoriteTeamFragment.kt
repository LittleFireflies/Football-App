package com.littlefireflies.footballclub.presentation.ui.favoriteteam


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.presentation.base.BaseFragment
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailActivity
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import kotlinx.android.synthetic.main.item_team_list.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class FavoriteTeamFragment : BaseFragment(), FavoriteTeamContract.View {

    @Inject
    lateinit var presenter: FavoriteTeamPresenter<FavoriteTeamContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_favorite_team

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
                presenter.getFavoriteTeamList()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTeamList()
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
        pbFavoriteTeam.show()
    }

    override fun hideLoading() {
        pbFavoriteTeam.hide()
    }

    override fun displayFavoriteTeamList(teams: List<FavoriteTeam>) {
        rvFavoriteTeam.adapter = FavoriteTeamAdapter(teams) {
            startActivity<TeamDetailActivity>(TeamDetailActivity.EXTRA_TEAM to it.teamId)
        }
        rvFavoriteTeam.layoutManager = LinearLayoutManager(context)
    }

    override fun displayErrorMessage(message: String) {
        pbFavoriteTeam.snackbar(message)
    }

    inner class FavoriteTeamAdapter(private val teams: List<FavoriteTeam>, val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false))

        override fun getItemCount(): Int = teams.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(teams[position])
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItem(team: FavoriteTeam) {
                Glide.with(context!!).load(team.teamBadge).into(itemView.ivBadge)
                itemView.tvTeamName.text = team.teamName
                itemView.setOnClickListener { listener(team) }
            }
        }
    }

}
