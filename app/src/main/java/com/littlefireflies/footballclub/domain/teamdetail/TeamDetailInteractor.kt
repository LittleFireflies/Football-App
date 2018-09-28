package com.littlefireflies.footballclub.domain.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDetailInteractor @Inject
constructor(val teamRepository: TeamRepository): TeamDetailUseCase{
    override fun getTeamDetail(teamId: String?): Single<Team> {
        return teamRepository.getTeamDetail(teamId)
                .flatMap {
                    Single.just(it.teams[0])
                }
    }
}