package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.Player
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
interface PlayerRepository {
    fun getPlayers(teamId: String): Single<List<Player>>
    fun getPlayerDetail(playerId: String): Single<Player>
}