package com.littlefireflies.footballclub.data

import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.data.model.TeamResponse
import com.littlefireflies.footballclub.data.network.NetworkHelper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class AppDataManager @Inject
constructor(var networkHelper: NetworkHelper) : DataManager {

    override fun getNextMatches(leagueId: String): Single<ScheduleResponse> {
        return networkHelper.getNextMatches(leagueId)
    }

    override fun getPreviousMatches(leagueId: String): Single<ScheduleResponse> {
        return networkHelper.getPreviousMatches(leagueId)
    }

    override fun getMatchDetail(matchId: String): Single<ScheduleResponse> {
        return networkHelper.getMatchDetail(matchId)
    }

    override fun getTeamDetail(teamId: String): Single<TeamResponse> {
        return networkHelper.getTeamDetail(teamId)
    }
}