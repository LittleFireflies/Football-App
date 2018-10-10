package com.littlefireflies.footballclub.data.repository.team

import com.littlefireflies.footballclub.data.model.TeamResponse
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */

interface TeamRepository {
    fun getTeamList(leagueId: String): Single<TeamResponse>
    fun getTeamDetail(teamId: String?): Single<TeamResponse>
}