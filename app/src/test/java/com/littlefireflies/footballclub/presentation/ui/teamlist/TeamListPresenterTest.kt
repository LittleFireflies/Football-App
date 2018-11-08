package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.domain.leaguelist.LeagueListUseCase
import com.littlefireflies.footballclub.domain.teamlist.TeamListUseCase
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
 * Created by widyarso.purnomo on 14/10/2018.
 */
class TeamListPresenterTest {

    @Mock
    private lateinit var teamListUseCase: TeamListUseCase
    @Mock
    private lateinit var leagueListUseCase: LeagueListUseCase
    @Mock
    private lateinit var view: TeamListContract.View

    private lateinit var leagueMock: League

    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: TeamListPresenter<TeamListContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val disposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        presenter = TeamListPresenter(leagueListUseCase, teamListUseCase, disposable, testSchedulerProvider)
        presenter.onAttach(view)

        leagueMock = League(leagueId = Constants.LEAGUE_ID)
    }

    @Test
    fun shouldDisplayTeamListMatchListWhenGetDataSuccess() {
        val response: MutableList<Team> = mutableListOf()

        `when`(view.selectedLeague).thenReturn(leagueMock)
        `when`(teamListUseCase.getTeamList(Constants.LEAGUE_ID)).thenReturn(Single.just(response))

        presenter.getTeamList()

        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).displayTeamList(response)
        verify(view).hideLoading()
    }

    @Test
    fun shouldDisplayErrorWhenGetDataFailed() {
        `when`(view.selectedLeague).thenReturn(leagueMock)
        `when`(teamListUseCase.getTeamList(Constants.LEAGUE_ID)).thenReturn(Single.error(Exception("Load Error")))

        presenter.getTeamList()

        testScheduler.triggerActions()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).displayErrorMessage("Unable to load team data")
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}