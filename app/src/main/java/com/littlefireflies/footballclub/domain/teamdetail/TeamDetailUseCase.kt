package com.littlefireflies.footballclub.domain.teamdetail

import com.littlefireflies.footballclub.data.model.Team
import io.reactivex.Single

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
interface TeamDetailUseCase {
    fun getTeamDetail(teamId: String?): Single<Team>
}