package com.littlefireflies.footballclub.ui.previousmatch

import com.littlefireflies.footballclub.data.DataManager
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.TestSchedulerProvider
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by widyarso.purnomo on 12/09/2018.
 */
class PreviousMatchPresenterTest {

    @Mock
    private lateinit var dataManager: DataManager
    @Mock
    private lateinit var view: PreviousMatchContract.View

    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: PreviousMatchPresenter<PreviousMatchContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val disposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        presenter = PreviousMatchPresenter(dataManager, disposable, testSchedulerProvider)
        presenter.onAttach(view)
    }

    @Test
    fun shouldDisplayMatchListWhenGetDataSuccess() {
        val matchList: MutableList<Match> = mutableListOf()
        val response = ScheduleResponse(matchList)

        `when`(dataManager.getPreviousMatches(Constants.LEAGUE_ID)).thenReturn(Single.just(response))

        presenter.getMatchList()

        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayMatchList(response.events)
        verify(view).hideLoading()
    }

    @Test
    fun shouldDisplayErrorWhenGetDataFailed() {

        `when`(dataManager.getPreviousMatches(Constants.LEAGUE_ID)).thenReturn(Single.error(Exception("Load Error")))

        presenter.getMatchList()

        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).displayErrorMessage("Unable to load the data")
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}