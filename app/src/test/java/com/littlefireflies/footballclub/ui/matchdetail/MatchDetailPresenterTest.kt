package com.littlefireflies.footballclub.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.model.ScheduleResponse
import com.littlefireflies.footballclub.utils.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by widyarso.purnomo on 12/09/2018.
 */
class MatchDetailPresenterTest {
    @Mock
    private lateinit var dataManager: DataManager
    @Mock
    private lateinit var view: MatchDetailContract.View

    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: MatchDetailPresenter<MatchDetailContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val disposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        presenter = MatchDetailPresenter(dataManager, disposable, testSchedulerProvider)
        presenter.onAttach(view)
    }

    @Test
    fun shouldDisplayMatchDetail() {
        val matchList: MutableList<Match> = mutableListOf()
        val response = ScheduleResponse(matchList)
        val matchId = "1"

        `when`(dataManager.getMatchDetail(matchId)).thenReturn(Single.just(response))

        presenter.getMatchDetail(matchId)
        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayMatch(response.events[0])
    }

    @Test
    fun shouldDisplayError() {
        val matchId = "1"
        `when`(dataManager.getMatchDetail(matchId)).thenReturn(Single.error(Exception("Load Error")))

        presenter.getMatchDetail(matchId)
        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayErrorMessages("Unable to load data")
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}