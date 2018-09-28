package com.littlefireflies.footballclub.domain.favoritematch

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface GetFavoriteMatchUseCase {
    fun getFavoriteMatchList(): Single<List<FavoriteMatch>>
    fun getFavoriteMatchStatus(matchId: String): Single<Boolean>
}