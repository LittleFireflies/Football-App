package com.littlefireflies.footballclub.data.repository.team

import android.content.Context
import com.littlefireflies.footballclub.data.database.database
import com.littlefireflies.footballclub.data.model.FavoriteTeam
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.network.NetworkService
import io.reactivex.Single
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class TeamDataStore
constructor(val networkService: NetworkService, val context: Context): TeamRepository{
    override fun getTeamList(leagueId: String): Single<List<Team>> {
        return networkService.getTeamList(leagueId)
                .flatMap {
                    val entities = mutableListOf<Team>()
                    it.teams.forEach { team ->
                        entities.add(team)
                    }
                    Single.just(entities)
                }
    }

    override fun getTeamDetail(teamId: String?): Single<Team> {
        return networkService.getTeamDetail(teamId)
                .flatMap {
                    Single.just(it.teams[0])
                }
    }

    override fun getTeamSearchResult(teamName: String): Single<List<Team>> {
        return networkService.searchTeamName(teamName)
                .flatMap {
                    val entities = mutableListOf<Team>()
                    it.teams.forEach { team ->
                        entities.add(team)
                    }
                    Single.just(entities)
                }
    }

    override fun getFavoriteTeamList(): Single<List<FavoriteTeam>> {
        var teamList: List<FavoriteTeam> = mutableListOf()

        context.database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
            teamList = result.parseList(classParser())
        }

        return Single.just(teamList)
    }

    override fun isFavorite(teamId: String): Single<Boolean> {
        var isFavorite = false

        context.database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE).whereArgs("(TEAM_ID = {id})", "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }

        return Single.just(isFavorite)
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