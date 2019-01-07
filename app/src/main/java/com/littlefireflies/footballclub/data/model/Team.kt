package com.littlefireflies.footballclub.data.model

import com.squareup.moshi.Json

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
data class Team(
        @field:Json(name = "idTeam")
        var teamId: String? = null,

        @field:Json(name = "strTeam")
        var teamName: String? = null,

        @field:Json(name = "strTeamBadge")
        var teamBadge: String? = null,

        @field:Json(name = "intFormedYear")
        var teamFormedYear: String? = null,

        @field:Json(name = "strStadium")
        var teamStadium: String? = null,

        @field:Json(name = "strDescriptionEN")
        var teamOverview: String? = null
)