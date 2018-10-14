package com.littlefireflies.footballclub.data.repository.match

import com.littlefireflies.footballclub.data.model.MatchResponse
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class MatchDataStore @Inject
constructor(val networkService: NetworkService): MatchRepository {
    override fun getNextMatch(leagueId: String): Single<MatchResponse> {
        return networkService.getNextMatches(leagueId)
    }

    override fun getPreviousMatch(leagueId: String): Single<MatchResponse> {
        return networkService.getPreviousMatches(leagueId)
    }

    override fun getMatchDetail(matchId: String): Single<MatchResponse> {
        return networkService.getMatchDetail(matchId)
    }

    override fun getMatchSearchResult(matchName: String): Single<MatchResponse> {
        return networkService.searchMatch(matchName)
    }
}