package com.littlefireflies.footballclub.domain.playerlist

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class PlayerListInteractor @Inject
constructor(private val playerRepository: PlayerRepository) : PlayerListUseCase {
    override fun getPlayerList(teamId: String): Single<List<Player>> {
        return playerRepository.getPlayers(teamId)
                .flatMap {
                    val entities = mutableListOf<Player>()
                    it.player.forEach { player ->
                        entities.add(player)
                    }
                    Single.just(entities)
                }
    }
}