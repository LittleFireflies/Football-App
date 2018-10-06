package com.littlefireflies.footballclub.domain.playerdetail

import com.littlefireflies.footballclub.data.model.Player
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 06/10/2018.
 */
interface PlayerDetailUseCase {
    fun getPlayerDetail(playerId: String): Single<Player>
}