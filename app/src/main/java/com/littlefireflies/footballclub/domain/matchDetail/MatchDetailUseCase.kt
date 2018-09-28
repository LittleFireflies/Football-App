package com.littlefireflies.footballclub.domain.matchDetail

import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface MatchDetailUseCase {
    fun getMatchDetail(matchId: String): Single<Match>
}