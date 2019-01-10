package com.littlefireflies.footballclub.data.repository.league

import com.littlefireflies.footballclub.data.model.League

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
interface LeagueRepository {
    fun saveLeagueList(leagues: List<League>)
    suspend fun getSoccerLeagueList(): List<League>
    suspend fun getNetworkLeagueList(): List<League>
    suspend fun getLocalLeagueList(): List<League>
}