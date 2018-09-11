package com.littlefireflies.footballclub.data.network

import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.data.model.TeamResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class AppNetworkHelper @Inject
constructor(private val networkService: NetworkService) : NetworkHelper {

    override fun getNextMatches(leagueId: String): Single<ScheduleResponse> {
        return networkService.getNextMatches(leagueId)
    }

    override fun getPreviousMatches(leagueId: String): Single<ScheduleResponse> {
        return networkService.getPreviousMatches(leagueId)
    }

    override fun getMatchDetail(matchId: String): Single<ScheduleResponse> {
        return networkService.getMatchDetail(matchId)
    }

    override fun getTeamDetail(teamId: String): Single<TeamResponse> {
        return networkService.getTeamDetail(teamId)
    }
}