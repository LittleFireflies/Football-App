package com.littlefireflies.footballclub.presentation.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.utils.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by widyarso.purnomo on 12/09/2018.
 */
class MatchDetailPresenterTest {
    @Mock
    private lateinit var matchDetailUseCase: MatchRepository
    @Mock
    private lateinit var teamRepository: TeamRepository
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

        presenter = MatchDetailPresenter(matchDetailUseCase, teamRepository, disposable, testSchedulerProvider)
        presenter.onAttach(view)
    }

    @Test
    fun shouldDisplayMatchDetail() {
        val response = Match()
        val matchId = "3242"

        `when`(matchDetailUseCase.getMatchDetail(matchId)).thenReturn(Single.just(response))

        presenter.getMatchDetail(matchId)
        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayMatch(response)
    }

    @Test
    fun shouldDisplayError() {
        val matchId = "1"
        `when`(matchDetailUseCase.getMatchDetail(matchId)).thenReturn(Single.error(Exception("Load Error")))

        presenter.getMatchDetail(matchId)
        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayErrorMessages("Unable to load the data")
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}