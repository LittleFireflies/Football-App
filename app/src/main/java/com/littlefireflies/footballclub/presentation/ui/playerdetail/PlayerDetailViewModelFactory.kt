package com.littlefireflies.footballclub.presentation.ui.playerdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository

class PlayerDetailViewModelFactory
constructor(private val playerRepository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerDetailViewModel(playerRepository) as T
    }
}