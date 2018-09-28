package com.littlefireflies.footballclub.domain.favoritematch

import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchRepository
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class RemoveFavoriteMatchInteractor @Inject
constructor(val favoriteMatchRepository: FavoriteMatchRepository): RemoveFavoriteMatchUseCase{
    override fun removeFromFavorite(matchId: String) {
        return favoriteMatchRepository.removeFromFavorite(matchId)
    }
}