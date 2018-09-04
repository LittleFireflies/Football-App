package com.littlefireflies.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
        var awayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoals: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoals: String? = null,

        @SerializedName("strHomeRedCards")
        var homeRedCards: String? = null,

        @SerializedName("strAwayRedCards")
        var awayRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        var homeYellowCards: String? = null,

        @SerializedName("strAwayYellowCards")
        var awayYellowCards: String? = null,

        @SerializedName("strHomeFormation")
        var homeFormation: String? = null,

        @SerializedName("strAwayFormation")
        var awayFormation: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeGK: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var homeSubs: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayGK: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayForward: String? = null,

        @SerializedName("strAwaySubstitutes")
        var awaySubs: String? = null
)