package com.littlefireflies.footballclub.domain.matchlist

import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchListUseCase {
    fun getNextMatchList(leagueId: String?): Single<List<Match>>
    fun getPreviousMatchList(leagueId: String?): Single<List<Match>>
    fun getMatchSearchResult(matchName: String): Single<List<Match>>
}