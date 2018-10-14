package com.littlefireflies.footballclub.presentation.ui.matchdetail

import com.littlefireflies.footballclub.data.model.Match
import com.littlefireflies.footballclub.domain.favoritematch.AddFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.favoritematch.GetFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.favoritematch.RemoveFavoriteMatchUseCase
import com.littlefireflies.footballclub.domain.matchdetail.MatchDetailUseCase
import com.littlefireflies.footballclub.domain.teamdetail.TeamDetailUseCase
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
    private lateinit var matchDetailUseCase: MatchDetailUseCase
    @Mock
    private lateinit var getFavoriteMatchUseCase: GetFavoriteMatchUseCase
    @Mock
    private lateinit var addFavoriteMatchUseCase: AddFavoriteMatchUseCase
    @Mock
    private lateinit var removeFavoriteMatchUseCase: RemoveFavoriteMatchUseCase
    @Mock
    private lateinit var teamDetailUseCase: TeamDetailUseCase
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

        presenter = MatchDetailPresenter(matchDetailUseCase, getFavoriteMatchUseCase, addFavoriteMatchUseCase, removeFavoriteMatchUseCase, teamDetailUseCase, disposable, testSchedulerProvider)
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