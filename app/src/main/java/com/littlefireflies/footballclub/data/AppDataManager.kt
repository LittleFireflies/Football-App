package com.littlefireflies.footballclub.data

import com.littlefireflies.footballclub.data.database.DbHelper
import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.data.model.TeamResponse
import com.littlefireflies.footballclub.data.network.NetworkHelper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class AppDataManager @Inject
constructor(private var networkHelper: NetworkHelper, private var dbHelper: DbHelper) : DataManager {

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

    override fun getFavoriteMatches(): Single<List<FavoriteMatch>> {
        return dbHelper.getFavoriteMatches()
    }

    override fun isFavorite(id: String): Single<Boolean> {
        return dbHelper.isFavorite(id)
    }

    override fun addToFavorite(match: Match) {
        return dbHelper.addToFavorite(match)
    }

    override fun removeFromFavorite(id: String) {
        return dbHelper.removeFromFavorite(id)
    }
}