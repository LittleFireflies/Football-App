package com.littlefireflies.footballclub.domain.playerdetail

import com.littlefireflies.footballclub.data.model.Player
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
class PlayerDetailInteractor @Inject
constructor(val playerRepository: PlayerRepository): PlayerDetailUseCase {
    override fun getPlayerDetail(playerId: String): Single<Player> {
        return playerRepository.getPlayerDetail(playerId)
                .flatMap {
                    Single.just(it.players[0])
                }
    }
}