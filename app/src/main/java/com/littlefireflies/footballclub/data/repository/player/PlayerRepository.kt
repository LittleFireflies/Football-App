package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.Player

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
interface PlayerRepository {
    suspend fun getPlayers(teamId: String): List<Player>
    suspend fun getPlayerDetail(playerId: String): Player
}