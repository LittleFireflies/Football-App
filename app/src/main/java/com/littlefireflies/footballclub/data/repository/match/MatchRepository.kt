package com.littlefireflies.footballclub.data.repository.match

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.model.MatchResponse
import retrofit2.Response

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchRepository {
    suspend fun getNextMatch(leagueId: String): Response<MatchResponse>
    suspend fun getPreviousMatch(leagueId: String): Response<MatchResponse>
    suspend fun getMatchDetail(matchId: String): Response<MatchResponse>
    suspend fun getMatchSearchResult(matchName: String): List<Match>
    suspend fun getFavoriteMatches(): List<FavoriteMatch>
    suspend fun isFavorite(matchId: String): Boolean
    fun addToFavorite(match: Match)
    fun removeFromFavorite(matchId: String)
}