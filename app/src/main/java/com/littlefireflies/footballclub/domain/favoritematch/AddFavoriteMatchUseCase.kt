package com.littlefireflies.footballclub.domain.favoritematch

import com.littlefireflies.footballclub.data.model.Match

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface AddFavoriteMatchUseCase {
    fun addToFavorite(match: Match)
}