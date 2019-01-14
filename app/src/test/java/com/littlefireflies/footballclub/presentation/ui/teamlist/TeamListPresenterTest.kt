package com.littlefireflies.footballclub.presentation.ui.teamlist

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.Team
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.utils.Constants
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by widyarso.purnomo on 14/10/2018.
 */
class TeamListPresenterTest {

    @Mock
    private lateinit var teamRepository: TeamRepository
    @Mock
    private lateinit var leagueRepository: LeagueRepository
    @Mock
    private lateinit var view: TeamListContract.View

    private lateinit var leagueMock: League

    private lateinit var presenter: TeamListPresenter<TeamListContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = TeamListPresenter(leagueRepository, teamRepository)
        presenter.onAttach(view)

        leagueMock = League(leagueId = Constants.LEAGUE_ID)
    }

    @Ignore
    fun shouldDisplayTeamListMatchListWhenGetDataSuccess() {
        val response: MutableList<Team> = mutableListOf()

        `when`(view.selectedLeague).thenReturn(leagueMock)
//        `when`(teamRepository.getTeamList(Constants.LEAGUE_ID)).thenReturn(Single.just(response))
//
//        presenter.getTeamList()
//
//        testScheduler.triggerActions()
//
//        verify(view).showLoading()
//        verify(view).displayTeamList(response)
//        verify(view).hideLoading()
    }

    @Ignore
    fun shouldDisplayErrorWhenGetDataFailed() {
        `when`(view.selectedLeague).thenReturn(leagueMock)
//        `when`(teamRepository.getTeamList(Constants.LEAGUE_ID)).thenReturn(Single.error(Exception("Load Error")))
//
//        presenter.getTeamList()
//
//        testScheduler.triggerActions()
//
//        verify(view).showLoading()
//        verify(view).hideLoading()
//        verify(view).displayErrorMessage("Unable to load team data")
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}