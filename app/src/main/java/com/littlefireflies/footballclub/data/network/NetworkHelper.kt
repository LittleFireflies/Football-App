package com.littlefireflies.footballclub.data.network

import com.littlefireflies.footballclub.data.model.ScheduleResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NetworkHelper {
    fun getNextMatches(leagueId: String): Single<ScheduleResponse>
    fun getPreviousMatches(leagueId: String): Single<ScheduleResponse>
}