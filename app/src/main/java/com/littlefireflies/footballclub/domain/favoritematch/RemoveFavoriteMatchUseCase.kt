package com.littlefireflies.footballclub.domain.favoritematch

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface RemoveFavoriteMatchUseCase {
    fun removeFromFavorite(matchId: String)
}