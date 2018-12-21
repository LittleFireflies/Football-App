package com.littlefireflies.footballclub.data.repository.match

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchRepository {
    fun getNextMatch(leagueId: String): Single<List<Match>>
    fun getPreviousMatch(leagueId: String): Single<List<Match>>
    fun getMatchDetail(matchId: String): Single<Match>
    fun getMatchSearchResult(matchName: String): Single<List<Match>>
    fun getFavoriteMatches(): Single<List<FavoriteMatch>>
    fun isFavorite(matchId: String): Single<Boolean>
    fun addToFavorite(match: Match)
    fun removeFromFavorite(matchId: String)
}