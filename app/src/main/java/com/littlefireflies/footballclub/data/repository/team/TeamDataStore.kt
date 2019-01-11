package com.littlefireflies.footballclub.data.repository.team

import android.content.Context
import com.littlefireflies.footballclub.data.database.database
import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.network.NetworkService
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDataStore
constructor(private val networkService: NetworkService, val context: Context) : TeamRepository {
    override suspend fun getTeamList(leagueId: String): List<Team> {
        return networkService.getTeamList(leagueId).await().teams
    }

    override suspend fun getTeamDetail(teamId: String?): Team {
        return networkService.getTeamDetail(teamId).await().teams[0]
    }

    override suspend fun getTeamSearchResult(teamName: String): List<Team> {
        return networkService.searchTeamName(teamName).await().teams
    }

    override suspend fun getFavoriteTeamList(): List<FavoriteTeam> {
        var teamList: List<FavoriteTeam> = mutableListOf()

        context.database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
            teamList = result.parseList(classParser())
        }

        return teamList
    }

    override suspend fun isFavorite(teamId: String): Boolean {
        var isFavorite = false

        context.database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE).whereArgs("(TEAM_ID = {id})", "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }

        return isFavorite
    }

    override fun addtoFavorite(team: Team) {
        context.database.use {
            insert(FavoriteTeam.TABLE_TEAM_FAVORITE,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge)
        }
    }

    override fun removeFromFavorite(teamId: String) {
        context.database.use {
            delete(FavoriteTeam.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})", "id" to teamId)
        }
    }
}