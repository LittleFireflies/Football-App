package com.littlefireflies.footballclub.data.repository.team

import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.data.model.Team

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */

interface TeamRepository {
    suspend fun getTeamList(leagueId: String): List<Team>
    suspend fun getTeamDetail(teamId: String?): Team
    suspend fun getTeamSearchResult(teamName: String): List<Team>
    suspend fun getFavoriteTeamList(): List<FavoriteTeam>
    suspend fun isFavorite(teamId: String): Boolean
    fun addtoFavorite(team: Team)
    fun removeFromFavorite(teamId: String)
}