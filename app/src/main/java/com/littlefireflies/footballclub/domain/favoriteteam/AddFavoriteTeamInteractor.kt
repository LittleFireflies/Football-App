package com.littlefireflies.footballclub.domain.favoriteteam

import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class AddFavoriteTeamInteractor @Inject
constructor(val teamRepository: TeamRepository) : AddFavoriteTeamUseCase {
    override fun addToFavorite(team: Team) {
        teamRepository.addtoFavorite(team)
    }
}