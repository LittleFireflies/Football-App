package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.network.NetworkService

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class PlayerDataStore
constructor(val networkService: NetworkService) : PlayerRepository{
    override suspend fun getPlayers(teamId: String): List<Player> {
        return networkService.getPlayersByTeam(teamId).await().player
    }

    override suspend fun getPlayerDetail(playerId: String): Player {
        return networkService.getPlayerDetail(playerId).await().players[0]
    }
}