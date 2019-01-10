package com.littlefireflies.footballclub.presentation.ui.favoritematch

import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by widyarso.purnomo on 08/09/2018.
 */
class FavoriteMatchPresenter<V : FavoriteMatchContract.View>
constructor(private val matchRepository: MatchRepository) : BasePresenter<V>(), FavoriteMatchContract.UserActionListener<V> {

    override fun getFavoriteMatchList() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = matchRepository.getFavoriteMatches()
            view?.displayFavoriteMatchList(data)
            view?.hideLoading()
        }
        //TODO:
//                            view?.hideLoading()
//                            view?.displayErrorMessages("Unable to load Favorite matches")
    }
}