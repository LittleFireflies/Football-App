package com.littlefireflies.footballclub.domain.favoriteteam

import com.littlefireflies.footballclub.data.model.Team

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
interface AddFavoriteTeamUseCase {
    fun addToFavorite(team: Team)
}