package com.littlefireflies.footballclub.domain.leaguelist

import com.littlefireflies.footballclub.data.model.League
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
interface LeagueListUseCase {
    fun getSoccerLeagueList(): Single<List<League>>
    fun saveSoccerLeagueList(leagues: List<League>)
}