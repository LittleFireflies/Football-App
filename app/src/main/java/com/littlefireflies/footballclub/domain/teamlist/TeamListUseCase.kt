package com.littlefireflies.footballclub.domain.teamlist

import com.littlefireflies.footballclub.data.model.Team
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 10/10/2018.
 */
interface TeamListUseCase {
    fun getTeamList(leagueId: String?): Single<List<Team>>
}