package com.littlefireflies.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */
data class Team(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)