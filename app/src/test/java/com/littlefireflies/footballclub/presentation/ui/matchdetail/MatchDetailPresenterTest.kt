package com.littlefireflies.footballclub.presentation.ui.matchdetail

import com.littlefireflies.footballclub.data.model.MatchResponse
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
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
class MatchDetailPresenterTest {
    @Mock
    private lateinit var matchRepository: MatchRepository
    @Mock
    private lateinit var teamRepository: TeamRepository
    @Mock
    private lateinit var view: MatchDetailContract.View
    @Mock
    private lateinit var matchResponse: Response<MatchResponse>

    private lateinit var presenter: MatchDetailPresenter<MatchDetailContract.View>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchDetailPresenter(matchRepository, teamRepository, TestContextProvider())
        presenter.onAttach(view)
    }

    @Test
    fun shouldDisplayMatchDetail() {
        val matchId = "3242"

        runBlocking {
            `when`(matchRepository.getMatchDetail(matchId)).thenReturn(matchResponse)
            `when`(matchResponse.isSuccessful).thenReturn(true)
            `when`(matchResponse.code()).thenReturn(200)

            `when`(matchRepository.isFavorite(matchId)).thenReturn(false)

            presenter.getMatchDetail(matchId)

            verify(view).showLoading()
            matchResponse.body()?.events?.get(0)?.let { verify(view).displayMatch(it, false) }
            verify(view).hideLoading()
        }
    }

    @Test
    fun shouldDisplayError() {
        val matchId = "1"

        runBlocking {
            `when`(matchRepository.getMatchDetail(matchId)).thenReturn(matchResponse)
            `when`(matchResponse.isSuccessful).thenReturn(false)

            `when`(matchRepository.isFavorite(matchId)).thenReturn(false)

            presenter.getMatchDetail(matchId)

            verify(view).showLoading()
            verify(view).hideLoading()
            verify(view).displayErrorMessages(ArgumentMatchers.anyString())
        }
    }

    @After
    fun tearDown() {
        presenter.onDetach()
    }
}