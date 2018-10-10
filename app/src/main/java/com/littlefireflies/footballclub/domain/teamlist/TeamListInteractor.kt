package com.littlefireflies.footballclub.domain.teamlist

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
class TeamListInteractor @Inject
constructor(val teamRepository: TeamRepository) : TeamListUseCase{
    override fun getTeamList(leagueId: String?): Single<List<Team>> {
        return teamRepository.getTeamList(leagueId.toString())
                .flatMap {
                    val entities = mutableListOf<Team>()
                    it.teams.forEach { team ->
                        entities.add(team)
                    }
                    Single.just(entities)
                }
    }
}