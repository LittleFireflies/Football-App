package com.littlefireflies.footballclub.data.database

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
interface DbHelper {
    fun getFavoriteMatches(): Single<List<FavoriteMatch>>
    fun isFavorite(id: String): Single<Boolean>
    fun addToFavorite(match: Match)
    fun removeFromFavorite(id: String)
}