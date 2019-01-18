package com.littlefireflies.footballclub.data.network

import com.littlefireflies.footballclub.data.model.LeagueResponse
import com.littlefireflies.footballclub.data.model.MatchResponse
import com.littlefireflies.footballclub.data.model.PlayerResponse
import com.littlefireflies.footballclub.data.model.TeamResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
interface NetworkService {

    @GET("v1/json/1/eventspastleague.php")
    fun getPreviousMatches(@Query("id") leagueId: String): Deferred<Response<MatchResponse>>

    @GET("v1/json/1/eventsnextleague.php")
    fun getNextMatches(@Query("id") leagueId: String): Deferred<Response<MatchResponse>>

    @GET("v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String): Deferred<Response<MatchResponse>>

    @GET("v1/json/1/lookup_all_teams.php")
    fun getTeamList(@Query("id") leagueId: String): Deferred<TeamResponse>

    @GET("v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String?): Deferred<TeamResponse>

    @GET("v1/json/1/lookup_all_players.php")
    fun getPlayersByTeam(@Query("id") teamId: String?): Deferred<PlayerResponse>

    @GET("v1/json/1/lookupplayer.php")
    fun getPlayerDetail(@Query("id") playerId: String): Deferred<PlayerResponse>

    @GET("v1/json/1/all_leagues.php")
    fun getLeagueList(): Deferred<LeagueResponse>

    @GET("v1/json/1/searchteams.php")
    fun searchTeamName(@Query("t") teamName: String): Deferred<TeamResponse>

    @GET("v1/json/1/searchevents.php")
    fun searchMatch(@Query("e") matchName: String): Deferred<MatchResponse>
}