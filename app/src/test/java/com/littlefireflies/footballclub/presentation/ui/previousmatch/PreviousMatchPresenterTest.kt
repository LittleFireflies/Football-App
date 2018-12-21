package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.utils.Constants
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
class PreviousMatchPresenterTest {

    @Mock
    private lateinit var matchRepository: MatchRepository
    @Mock
    private lateinit var leagueRepository: LeagueRepository
    @Mock
    private lateinit var view: PreviousMatchContract.View

    private lateinit var leagueMock: League

    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: PreviousMatchPresenter<PreviousMatchContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val disposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        presenter = PreviousMatchPresenter(matchRepository, leagueRepository, disposable, testSchedulerProvider)
        presenter.onAttach(view)

        leagueMock = League(leagueId = Constants.LEAGUE_ID)
    }

    @Test
    fun shouldDisplayMatchListWhenGetDataSuccess() {
        val response: MutableList<Match> = mutableListOf()

        `when`(view.selectedLeague).thenReturn(leagueMock)
        `when`(matchRepository.getPreviousMatch(Constants.LEAGUE_ID)).thenReturn(Single.just(response))

        presenter.getMatchList()

        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayMatchList(response)
        verify(view).hideLoading()
    }

    @Test
    fun shouldDisplayErrorWhenGetDataFailed() {

        `when`(view.selectedLeague).thenReturn(leagueMock)
        `when`(matchRepository.getPreviousMatch(Constants.LEAGUE_ID)).thenReturn(Single.error(Exception("Load Error")))

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