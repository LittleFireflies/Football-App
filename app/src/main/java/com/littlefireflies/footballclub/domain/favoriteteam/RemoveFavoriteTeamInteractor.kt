package com.littlefireflies.footballclub.domain.favoriteteam

import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class RemoveFavoriteTeamInteractor @Inject
constructor(val teamRepository: TeamRepository) : RemoveFavoriteTeamUseCase {
    override fun removeFavoriteTeam(teamId: String) {
        teamRepository.removeFromFavorite(teamId)
    }
}