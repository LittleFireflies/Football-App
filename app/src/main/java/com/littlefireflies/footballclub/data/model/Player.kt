package com.littlefireflies.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by widyarso.purnomo on 29/09/2018.
 */
data class Player(
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strTeam")
        var team: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strNationality")
        var nationality: String? = null,

        @SerializedName("dateBorn")
        var bornDate: String? = null,

        @SerializedName("strDescriptionEN")
        var description: String? = null,

        @SerializedName("strPosition")
        var position: String? = null,

        @SerializedName("strHeight")
        var height: String? = null,

        @SerializedName("strWeight")
        var weight: String? = null,

        @SerializedName("strThumb")
        var picture: String? = null,

        @SerializedName("strCutout")
        var imageCutout: String? = null
)