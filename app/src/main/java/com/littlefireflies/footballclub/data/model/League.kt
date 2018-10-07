package com.littlefireflies.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widyarso.purnomo on 07/10/2018.
 */
data class League(
        @SerializedName("idLeague")
        var leagueId: String? = null,

        @SerializedName("strLeague")
        var leagueName: String? = null,

        @SerializedName("strSport")
        var sport: String? = null) {

    companion object {
        const val TABLE_LEAGUE = "TABLE_LEAGUE"
        const val LEAGUE_ID = "ID_"
        const val LEAGUE_NAME = "LEAGUE_NAME"
        const val LEAGUE_SPORT = "LEAGUE_SPORT"
    }

    override fun toString(): String {
        return if (leagueName == null) "" else leagueName.toString()
    }
}