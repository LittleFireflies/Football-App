package com.littlefireflies.footballclub.data.repository.match

import android.content.Context
import com.littlefireflies.footballclub.data.database.database
import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.network.NetworkService
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class MatchDataStore
constructor(val networkService: NetworkService, val context: Context) : MatchRepository {
    override suspend fun getNextMatch(leagueId: String): List<Match> {
        return networkService.getNextMatches(leagueId).await().events
    }

    override suspend fun getPreviousMatch(leagueId: String): List<Match> {
        return networkService.getPreviousMatches(leagueId).await().events
    }

    override suspend fun getMatchDetail(matchId: String): Match {
        return networkService.getMatchDetail(matchId).await().events[0]
    }

    override suspend fun getMatchSearchResult(matchName: String): List<Match> {
        val result = networkService.searchMatch(matchName).await()
        return result.event
    }

    override suspend fun getFavoriteMatches(): List<FavoriteMatch> {
        var favoriteList: List<FavoriteMatch> = mutableListOf()

        context.database.use {
            val result = select(FavoriteMatch.TABLE_MATCH_FAVORITE)
            favoriteList = result.parseList(classParser())
        }

        return favoriteList
    }

    override suspend fun isFavorite(matchId: String): Boolean {
        var isFavorite = false

        context.database.use {
            val result = select(FavoriteMatch.TABLE_MATCH_FAVORITE).whereArgs("(MATCH_ID = {id})", "id" to matchId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }

        return isFavorite
    }

    override fun addToFavorite(match: Match) {
        context.database.use {
            insert(FavoriteMatch.TABLE_MATCH_FAVORITE,
                    FavoriteMatch.MATCH_ID to match.matchId,
                    FavoriteMatch.MATCH_NAME to match.matchName,
                    FavoriteMatch.MATCH_LEAGUE to match.league,
                    FavoriteMatch.HOME_TEAM_ID to match.homeTeamId,
                    FavoriteMatch.HOME_TEAM to match.homeTeam,
                    FavoriteMatch.AWAY_TEAM_ID to match.awayTeamId,
                    FavoriteMatch.AWAY_TEAM to match.awayTeam,
                    FavoriteMatch.MATCH_DATE to match.matchDate,
                    FavoriteMatch.MATCH_TIME to match.matchTime,
                    FavoriteMatch.HOME_SCORE to match.homeScore,
                    FavoriteMatch.AWAY_SCORE to match.awayScore)
        }
    }

    override fun removeFromFavorite(matchId: String) {
        context.database.use {
            delete(FavoriteMatch.TABLE_MATCH_FAVORITE, "(MATCH_ID = {id})", "id" to matchId)
        }
    }
}