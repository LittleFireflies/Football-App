package com.littlefireflies.footballclub.data.repository.league

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.LeagueResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
interface LeagueRepository {
    fun saveLeagueList(leagues: List<League>)
    fun getNetworkLeagueList() : Single<LeagueResponse>
    fun getLocalLeagueList() : List<League>
}