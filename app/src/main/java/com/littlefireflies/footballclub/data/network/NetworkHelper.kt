package com.littlefireflies.footballclub.data.network

import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NetworkHelper {
    fun getNextMatches(leagueId: String): Single<String>
    fun getPreviousMatches(leagueId: String): Single<String>
}