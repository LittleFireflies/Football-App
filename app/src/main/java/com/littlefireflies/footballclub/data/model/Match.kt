package com.littlefireflies.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
data class Match(
        @SerializedName("idEvent")
        var matchId: String? = null,

        @SerializedName("strEvent")
        var matchName: String? = null,

        @SerializedName("strLeague")
        var league: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("dateEvent")
        var matchDate: String? = null,

        @SerializedName("strTime")
        var matchTime: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null
)