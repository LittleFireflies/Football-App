package com.littlefireflies.footballclub.presentation.ui.playerdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayerDetailViewModel
constructor(private val playerRepository: PlayerRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    val playerData = MutableLiveData<Player>()
    val errorMessage = MutableLiveData<String>()

    fun getPlayerDetail(playerId: String) {
        disposable.add(
                playerRepository.getPlayerDetail(playerId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            playerData.value = it
                        }, {
                            errorMessage.value = it.message
                        })
        )
    }
}
