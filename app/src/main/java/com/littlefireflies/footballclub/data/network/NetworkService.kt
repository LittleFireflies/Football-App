package com.littlefireflies.footballclub.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NetworkService {

    @GET("v1/json/1/eventspastleague.php")
    fun getPreviousMatches(@Query("id") leagueId: String): Single<String>

}