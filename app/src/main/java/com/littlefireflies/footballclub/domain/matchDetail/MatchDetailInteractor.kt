package com.littlefireflies.footballclub.domain.matchDetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class MatchDetailInteractor @Inject
constructor(val matchRepository: MatchRepository): MatchDetailUseCase{
    override fun getMatchDetail(matchId: String): Single<Match> {
        return matchRepository.getMatchDetail(matchId)
                .flatMap {
                    Single.just(it.events[0])
                }
    }
}