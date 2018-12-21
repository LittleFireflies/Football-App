package com.littlefireflies.footballclub.data.repository.team

import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.data.model.Team
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */

interface TeamRepository {
    fun getTeamList(leagueId: String): Single<List<Team>>
    fun getTeamDetail(teamId: String?): Single<Team>
    fun getTeamSearchResult(teamName: String): Single<List<Team>>
    fun getFavoriteTeamList(): Single<List<FavoriteTeam>>
    fun isFavorite(teamId: String): Single<Boolean>
    fun addtoFavorite(team: Team)
    fun removeFromFavorite(teamId: String)
}