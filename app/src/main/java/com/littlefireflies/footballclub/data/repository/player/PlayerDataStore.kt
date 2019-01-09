package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class PlayerDataStore
constructor(val networkService: NetworkService) : PlayerRepository{
    override fun getPlayers(teamId: String): Single<List<Player>> {
        return networkService.getPlayersByTeam(teamId)
                .flatMap {
                    val entities = mutableListOf<Player>()
                    it.player.forEach { player ->
                        entities.add(player)
                    }
                    Single.just(entities)
                }
    }

    override fun getPlayerDetail(playerId: String): Single<Player> {
        return networkService.getPlayerDetail(playerId)
                .flatMap {
                    Single.just(it.players[0])
                }
    }
}