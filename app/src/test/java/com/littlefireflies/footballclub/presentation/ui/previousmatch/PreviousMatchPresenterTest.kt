package com.littlefireflies.footballclub.presentation.ui.previousmatch

import com.littlefireflies.footballclub.data.model.League
import com.littlefireflies.footballclub.data.model.MatchResponse
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.TestContextProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response

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
    @Mock
    private lateinit var matchResponse: Response<MatchResponse>

    private lateinit var leagueMock: League

    private lateinit var presenter: PreviousMatchPresenter<PreviousMatchContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = PreviousMatchPresenter(matchRepository, leagueRepository, TestContextProvider())
        presenter.onAttach(view)

        leagueMock = League(leagueId = Constants.LEAGUE_ID)
    }

    @Test
    fun shouldDisplayMatchListWhenGetDataSuccess() {

        `when`(view.selectedLeague).thenReturn(leagueMock)

        runBlocking {
            `when`(matchRepository.getPreviousMatch(Constants.LEAGUE_ID)).thenReturn(matchResponse)
            `when`(matchResponse.isSuccessful).thenReturn(true)
            `when`(matchResponse.code()).thenReturn(200)

            presenter.getMatchList()

            verify(view).showLoading()
            verify(view).displayMatchList(matchResponse.body()?.events ?: mutableListOf())
            verify(view).hideLoading()
        }
    }

    @Test
    fun shouldDisplayErrorWhenGetDataFailed() {

        `when`(view.selectedLeague).thenReturn(leagueMock)

        runBlocking {
            `when`(matchRepository.getNextMatch(Constants.LEAGUE_ID)).thenReturn(matchResponse)
            `when`(matchResponse.isSuccessful).thenReturn(false)

            presenter.getMatchList()

            verify(view).showLoading()
            verify(view).hideLoading()
            verify(view).displayErrorMessage(ArgumentMatchers.anyString())
        }
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}