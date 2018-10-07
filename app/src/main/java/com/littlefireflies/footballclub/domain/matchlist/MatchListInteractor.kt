package com.littlefireflies.footballclub.domain.matchlist

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class MatchListInteractor @Inject
constructor(val matchRepository: MatchRepository): MatchListUseCase{

    override fun getNextMatchList(leagueId: String?): Single<List<Match>> {
        return matchRepository.getNextMatch(leagueId.toString())
                .flatMap {
                    val entities = mutableListOf<Match>()
                    it.events.forEach { match ->
                        entities.add(match)
                    }
                    Single.just(entities)
                }
    }

    override fun getPreviousMatchList(leagueId: String?): Single<List<Match>> {
        return matchRepository.getPreviousMatch(leagueId.toString())
                .flatMap {
                    val entities = mutableListOf<Match>()
                    it.events.forEach { match ->
                        entities.add(match)
                    }
                    Single.just(entities)
                }
    }
}