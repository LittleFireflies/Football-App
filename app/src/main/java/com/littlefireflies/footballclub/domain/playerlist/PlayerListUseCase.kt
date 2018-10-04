package com.littlefireflies.footballclub.domain.playerlist

import com.littlefireflies.footballclub.data.model.Player
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 29/09/2018.
 */
interface PlayerListUseCase {
    fun getPlayerList(teamId: String): Single<List<Player>>
}