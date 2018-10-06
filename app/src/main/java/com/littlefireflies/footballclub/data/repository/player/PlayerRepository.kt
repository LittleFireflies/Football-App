package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.PlayerResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
interface PlayerRepository {
    fun getPlayers(teamId: String): Single<PlayerResponse>
    fun getPlayerDetail(playerId: String): Single<PlayerResponse>
}