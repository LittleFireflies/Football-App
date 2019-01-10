package com.littlefireflies.footballclub.presentation.ui.playerdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailViewModel
constructor(private val playerRepository: PlayerRepository) : ViewModel() {

    val playerData = MutableLiveData<Player>()
    val errorMessage = MutableLiveData<String>()

    fun getPlayerDetail(playerId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = playerRepository.getPlayerDetail(playerId)
            playerData.value = data
        }
    }
}
