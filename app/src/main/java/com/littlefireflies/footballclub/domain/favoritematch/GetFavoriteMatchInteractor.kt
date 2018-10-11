package com.littlefireflies.footballclub.domain.favoritematch

import com.littlefireflies.footballclub.data.model.FavoriteMatch
import com.littlefireflies.footballclub.data.repository.favoritematch.FavoriteMatchRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by widyarso.purnomo on 28/09/2018.
 */
class GetFavoriteMatchInteractor @Inject
constructor(val favoriteMatchRepository: FavoriteMatchRepository): GetFavoriteMatchUseCase {
    override fun getFavoriteMatchList(): Single<List<FavoriteMatch>> {
        return favoriteMatchRepository.getFavoriteMatches()
    }

    override fun getFavoriteMatchStatus(matchId: String): Single<Boolean> {
        return favoriteMatchRepository.isFavorite(matchId)
    }
}