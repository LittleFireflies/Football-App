package com.littlefireflies.footballclub.data.model

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */

data class FavoriteMatch(val id: Long?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_NAME: String = "MATCH_NAME"
        const val MATCH_LEAGUE: String = "MATCH_LEAGUE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}