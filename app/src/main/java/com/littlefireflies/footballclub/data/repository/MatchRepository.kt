package com.littlefireflies.footballclub.data.repository

import com.littlefireflies.footballclub.data.model.ScheduleResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchRepository {
    fun getNextMatch(leagueId: String): Single<ScheduleResponse>
    fun getPreviousMatch(leagueId: String): Single<ScheduleResponse>
    fun getMatchDetail(matchId: String): Single<ScheduleResponse>
}