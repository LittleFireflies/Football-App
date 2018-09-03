package com.littlefireflies.footballclub.data.network

import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
class AppNetworkHelper @Inject
constructor(val networkService: NetworkService) : NetworkHelper {

    override fun getNextMatches(leagueId: String): Single<String> {
        return networkService.getNextMatches(leagueId)
    }

    override fun getPreviousMatches(leagueId: String): Single<String> {
        return networkService.getPreviousMatches(leagueId)
    }
}