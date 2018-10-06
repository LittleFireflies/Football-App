package com.littlefireflies.footballclub.presentation.ui.playerdetail

import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.presentation.base.BaseActivity
import com.littlefireflies.footballclub.utils.bornDateFormatter
import com.littlefireflies.footballclub.utils.hide
import com.littlefireflies.footballclub.utils.show
import kotlinx.android.synthetic.main.activity_player_detail.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class PlayerDetailActivity : BaseActivity(), PlayerDetailContract.View {

    @Inject
    lateinit var presenter: PlayerDetailPresenter<PlayerDetailContract.View>

    override fun getLayoutId(): Int = R.layout.activity_player_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityComponent.inject(this)
        onAttachView()
    }

    override fun onResume() {
        super.onResume()
        val intent =  intent
        presenter.getPlayerDetail(intent.getStringExtra("playerId"))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
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
        pbPlayerDetail.show()
    }

    override fun hideLoading() {
        pbPlayerDetail.hide()
    }

    override fun displayPlayer(player: Player) {
        setTitle(player.playerName)
        Glide.with(this).load(player.picture).into(ivThumbnail)
        tvWeight.text = if (player.weight?.isNotEmpty()!!) player.weight else "-"
        tvHeight.text = if (player.height?.isNotEmpty()!!) player.height else "-"
        tvDateBorn.text = bornDateFormatter(player.bornDate)
        tvNationality.text = player.nationality
        tvPosition.text = player.position
        tvDescription.text = player.description
    }

    override fun displayErrorMessage(message: String) {
        snackbar(pbPlayerDetail, message)
    }
}
