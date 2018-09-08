package com.littlefireflies.footballclub.ui.favoritematch


import android.os.Bundle
import android.support.v4.app.Fragment

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : BaseFragment(), FavoriteMatchContract.View {

    @Inject
    lateinit var presenter: FavoriteMatchPresenter<FavoriteMatchContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_favorite_match

    override fun onLoadFragment(saveInstance: Bundle?) {
        if (activityComponent != null) {
            activityComponent?.inject(this)
            onAttachView()
        }
    }

    override fun onDestroyView() {
        onDetachView()
        super.onDestroyView()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }
}
