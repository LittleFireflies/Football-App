package com.littlefireflies.footballclub.data.repository.league

import android.content.Context
import com.littlefireflies.footballclub.data.database.database
import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
class LeagueDataStore
constructor(val networkService: NetworkService, val context: Context) : LeagueRepository{
    override fun saveLeagueList(leagues: List<League>) {
        leagues.forEach {
            context.database.use {
                insert(League.TABLE_LEAGUE,
                        League.LEAGUE_ID to it.leagueId,
                        League.LEAGUE_NAME to it.leagueName,
                        League.LEAGUE_SPORT to it.sport)
            }
        }
    }

    override fun getNetworkLeagueList(): Single<List<League>> {
        return networkService.getLeagueList()
                .flatMap {
                    val entities = it.leagues.filter { league ->
                        league.sport == "Soccer"
                    }
                    Single.just(entities)
                }
    }

    override fun getLocalLeagueList(): List<League> {
        var leagues: List<League> = mutableListOf()

        context.database.use {
            val result = select(League.TABLE_LEAGUE)
            leagues = result.parseList(classParser())
        }

        return leagues
    }

    override fun getSoccerLeagueList(): Single<List<League>> {
        return if (getLocalLeagueList().isEmpty()) {
            getNetworkLeagueList()
        } else {
            Single.just(getLocalLeagueList())
        }
    }
}