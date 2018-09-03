package com.littlefireflies.footballclub.ui.MatchSchedule.NextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.littlefireflies.footballclub.R
import com.littlefireflies.footballclub.ui.base.BaseFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : BaseFragment(), NextMatchContract.View {

    @Inject
    lateinit var presenter: NextMatchPresenter<NextMatchContract.View>

    override fun getLayoutId(): Int = R.layout.fragment_next_match

    override fun onLoadFragment(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        onAttachView()
        presenter.getMatchList()
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
