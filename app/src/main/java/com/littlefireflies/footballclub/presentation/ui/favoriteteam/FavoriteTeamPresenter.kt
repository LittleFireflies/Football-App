package com.littlefireflies.footballclub.presentation.ui.favoriteteam

import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Widyarso Joko Purnomo on 11/10/18
 */
class FavoriteTeamPresenter<V : FavoriteTeamContract.View>
constructor(private val teamRepository: TeamRepository) : BasePresenter<V>(), FavoriteTeamContract.UserActionListener<V> {

    override fun getFavoriteTeamList() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = teamRepository.getFavoriteTeamList()
            view?.displayFavoriteTeamList(data)
            view?.hideLoading()
        }
        //TODO:
//                            view?.hideLoading()
//                            view?.displayErrorMessage("Unable to load the data")
    }
}