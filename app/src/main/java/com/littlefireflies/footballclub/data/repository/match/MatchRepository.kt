package com.littlefireflies.footballclub.data.repository.match

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchRepository {
    suspend fun getNextMatch(leagueId: String): List<Match>
    suspend fun getPreviousMatch(leagueId: String): List<Match>
    suspend fun getMatchDetail(matchId: String): Match
    suspend fun getMatchSearchResult(matchName: String): List<Match>
    suspend fun getFavoriteMatches(): List<FavoriteMatch>
    suspend fun isFavorite(matchId: String): Boolean
    fun addToFavorite(match: Match)
    fun removeFromFavorite(matchId: String)
}