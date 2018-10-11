package com.littlefireflies.footballclub.domain.favoritematch

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchRepository
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class AddFavoriteMatchInteractor @Inject
constructor(val favoriteMatchRepository: FavoriteMatchRepository): AddFavoriteMatchUseCase {
    override fun addToFavorite(match: Match) {
        return favoriteMatchRepository.addToFavorite(match)
    }
}