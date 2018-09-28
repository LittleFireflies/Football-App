package com.littlefireflies.footballclub.data.repository.favoritematch

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface FavoriteMatchRepository {
    fun getFavoriteMatches(): Single<List<FavoriteMatch>>
    fun isFavorite(matchId: String): Single<Boolean>
    fun addToFavorite(match: Match)
    fun removeFromFavorite(matchId: String)
}