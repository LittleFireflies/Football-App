package com.littlefireflies.footballclub.data.repository.player

import com.littlefireflies.footballclub.data.model.PlayerResponse
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 30/09/2018.
 */
class PlayerDataStore @Inject
constructor(val networkService: NetworkService) : PlayerRepository{
    override fun getPlayers(teamId: String): Single<PlayerResponse> {
        return networkService.getPlayersByTeam(teamId)
    }
}