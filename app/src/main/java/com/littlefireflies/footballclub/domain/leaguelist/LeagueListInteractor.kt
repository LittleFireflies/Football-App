package com.littlefireflies.footballclub.domain.leaguelist

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.LeagueResponse
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
class LeagueListInteractor @Inject
constructor(val leagueRepository: LeagueRepository) : LeagueListUseCase{
    override fun getSoccerLeagueList(): Single<List<League>> {
        return if (leagueRepository.getLocalLeagueList().size <= 0) {
            leagueRepository.getNetworkLeagueList()
                    .flatMap {
                        val entities = it.leagues.filter {league ->
                            league.sport.equals("Soccer")
                        }
                        Single.just(entities)
                    }
        } else {
            Single.just(leagueRepository.getLocalLeagueList())
        }
    }

    override fun saveSoccerLeagueList(leagues: List<League>) {
        leagueRepository.saveLeagueList(leagues)
    }
}