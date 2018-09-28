package com.littlefireflies.footballclub.data.repository.team

import com.littlefireflies.footballclub.data.model.TeamResponse
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDataStore @Inject
constructor(val networkService: NetworkService): TeamRepository{
    override fun getTeamDetail(teamId: String?): Single<TeamResponse> {
        return networkService.getTeamDetail(teamId)
    }
}