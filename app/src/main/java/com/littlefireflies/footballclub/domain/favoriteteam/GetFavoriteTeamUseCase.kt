package com.littlefireflies.footballclub.domain.favoriteteam

import com.littlefireflies.footballclub.data.model.FavoriteTeam
import io.reactivex.Single

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
interface GetFavoriteTeamUseCase {
    fun getFavoriteTeamList(): Single<List<FavoriteTeam>>
    fun getFavoriteTeamStatus(teamId: String): Single<Boolean>
}