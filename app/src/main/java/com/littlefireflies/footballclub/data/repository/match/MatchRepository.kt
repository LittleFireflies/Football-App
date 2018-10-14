package com.littlefireflies.footballclub.data.repository.match

import com.littlefireflies.footballclub.data.model.MatchResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchRepository {
    fun getNextMatch(leagueId: String): Single<MatchResponse>
    fun getPreviousMatch(leagueId: String): Single<MatchResponse>
    fun getMatchDetail(matchId: String): Single<MatchResponse>
    fun getMatchSearchResult(matchName: String): Single<MatchResponse>
}