package com.littlefireflies.footballclub.domain.favoriteteam

import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class GetFavoriteTeamInteractor @Inject
constructor(val teamRepository: TeamRepository) : GetFavoriteTeamUseCase {
    override fun getFavoriteTeamList(): Single<List<FavoriteTeam>> {
        return teamRepository.getFavoriteTeamList()
    }

    override fun getFavoriteTeamStatus(teamId: String): Single<Boolean> {
        return teamRepository.isFavorite(teamId)
    }
}