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
import org.koin.android.ext.android.inject

class PlayerDetailActivity : BaseActivity(), PlayerDetailContract.View {

    val presenter: PlayerDetailPresenter<PlayerDetailContract.View> by inject()

//    lateinit var factory: PlayerDetailViewModelFactory

//    lateinit var viewModel: PlayerDetailViewModel

    override fun getLayoutId(): Int = R.layout.activity_player_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        viewModel = ViewModelProviders.of(this, factory).get(PlayerDetailViewModel::class.java)
        onAttachView()
//        displayPlayer()
    }

    override fun onResume() {
        super.onResume()
        val intent =  intent
        presenter.getPlayerDetail(intent.getStringExtra("playerId"))
//        viewModel.getPlayerDetail(intent.getStringExtra("playerId"))
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
        title = player.playerName
        Glide.with(this@PlayerDetailActivity).load(player.picture).into(ivThumbnail)
        tvWeight.text = player.weight ?: "-"
        tvHeight.text = player.height ?: "-"
        tvDateBorn.text = bornDateFormatter(player.bornDate)
        tvNationality.text = player.nationality
        tvPosition.text = player.position
        tvDescription.text = player.description
    }

//    fun displayPlayer() {
//        viewModel.playerData.observe(this, Observer { player ->
//            title = player?.playerName
//            Glide.with(this@PlayerDetailActivity).load(player?.picture).into(ivThumbnail)
//            tvWeight.text = player?.weight ?: "-"
//            tvHeight.text = player?.height ?: "-"
//            tvDateBorn.text = bornDateFormatter(player?.bornDate)
//            tvNationality.text = player?.nationality
//            tvPosition.text = player?.position
//            tvDescription.text = player?.description
//        })
//    }

    override fun displayErrorMessage(message: String) {
        pbPlayerDetail.snackbar(message)
    }
}
