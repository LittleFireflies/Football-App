package com.littlefireflies.footballclub.data.repository

import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class MatchDataStore @Inject
constructor(val networkService: NetworkService): MatchRepository{
    override fun getNextMatch(leagueId: String): Single<ScheduleResponse> {
        return networkService.getNextMatches(leagueId)
    }
}