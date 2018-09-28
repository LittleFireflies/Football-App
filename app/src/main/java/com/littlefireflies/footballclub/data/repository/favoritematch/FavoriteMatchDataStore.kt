package com.littlefireflies.footballclub.data.repository.favoritematch

import android.content.Context
import com.littlefireflies.footballclub.data.database.database
import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.model.Match
import io.reactivex.Single
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class FavoriteMatchDataStore @Inject
constructor(val context: Context): FavoriteMatchRepository{
    override fun getFavoriteMatches(): Single<List<FavoriteMatch>> {
        var favoriteList: List<FavoriteMatch> = mutableListOf()

        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            favoriteList = result.parseList(classParser())
        }

        return Single.just(favoriteList)
    }

    override fun isFavorite(matchId: String): Single<Boolean> {
        var isFavorite = false

        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("(MATCH_ID = {id})", "id" to matchId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }

        return Single.just(isFavorite)
    }

    override fun addToFavorite(match: Match) {
        context.database.use {
            insert(FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.MATCH_ID to match.matchId,
                    FavoriteMatch.MATCH_NAME to match.matchName,
                    FavoriteMatch.MATCH_LEAGUE to match.league,
                    FavoriteMatch.HOME_TEAM to match.homeTeam,
                    FavoriteMatch.AWAY_TEAM to match.awayTeam,
                    FavoriteMatch.MATCH_DATE to match.matchDate,
                    FavoriteMatch.MATCH_TIME to match.matchTime,
                    FavoriteMatch.HOME_SCORE to match.homeScore,
                    FavoriteMatch.AWAY_SCORE to match.awayScore)
        }
    }

    override fun removeFromFavorite(matchId: String) {
        context.database.use {
            delete(FavoriteMatch.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to matchId)
        }
    }
}