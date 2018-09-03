package com.littlefireflies.footballclub.data

import com.littlefireflies.footballclub.data.network.NetworkHelper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class AppDataManager @Inject
constructor(var networkHelper: NetworkHelper) : DataManager {

    override fun getNextMatches(leagueId: String): Single<String> {
        return networkHelper.getNextMatches(leagueId)
    }

    override fun getPreviousMatches(leagueId: String): Single<String> {
        return networkHelper.getPreviousMatches(leagueId)
    }
}