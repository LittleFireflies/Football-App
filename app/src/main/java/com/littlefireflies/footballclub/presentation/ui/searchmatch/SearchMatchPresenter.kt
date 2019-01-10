package com.littlefireflies.footballclub.presentation.ui.searchmatch

import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.presentation.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Widyarso Joko Purnomo on 13/10/18
 */
class SearchMatchPresenter<V : SearchMatchContract.View>
constructor(private val matchRepository: MatchRepository) : BasePresenter<V>(), SearchMatchContract.UserActionListener<V> {

    override fun searchMatch(matchName: String) {
        view?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = matchRepository.getMatchSearchResult(matchName)
                view?.displayMatch(data)
                view?.hideLoading()
            } catch (e: Exception) {
                view?.hideLoading()
                view?.displayErrorMessage("Unable to load the data")
            }
        }

    }
}