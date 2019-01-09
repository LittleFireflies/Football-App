package com.littlefireflies.footballclub.di

import com.littlefireflies.footballclub.data.network.NetworkService
import com.littlefireflies.footballclub.data.repository.league.LeagueDataStore
import com.littlefireflies.footballclub.data.repository.league.LeagueRepository
import com.littlefireflies.footballclub.data.repository.match.MatchDataStore
import com.littlefireflies.footballclub.data.repository.match.MatchRepository
import com.littlefireflies.footballclub.data.repository.player.PlayerDataStore
import com.littlefireflies.footballclub.data.repository.player.PlayerRepository
import com.littlefireflies.footballclub.data.repository.team.TeamDataStore
import com.littlefireflies.footballclub.data.repository.team.TeamRepository
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchContract
import com.littlefireflies.footballclub.presentation.ui.favoritematch.FavoriteMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.favoriteteam.FavoriteTeamContract
import com.littlefireflies.footballclub.presentation.ui.favoriteteam.FavoriteTeamPresenter
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailContract
import com.littlefireflies.footballclub.presentation.ui.matchdetail.MatchDetailPresenter
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchContract
import com.littlefireflies.footballclub.presentation.ui.nextmatch.NextMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.playerdetail.PlayerDetailContract
import com.littlefireflies.footballclub.presentation.ui.playerdetail.PlayerDetailPresenter
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchContract
import com.littlefireflies.footballclub.presentation.ui.previousmatch.PreviousMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.searchmatch.SearchMatchContract
import com.littlefireflies.footballclub.presentation.ui.searchmatch.SearchMatchPresenter
import com.littlefireflies.footballclub.presentation.ui.splash.SplashContract
import com.littlefireflies.footballclub.presentation.ui.splash.SplashPresenter
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailContract
import com.littlefireflies.footballclub.presentation.ui.teamdetail.TeamDetailPresenter
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersContract
import com.littlefireflies.footballclub.presentation.ui.teamdetail.players.TeamPlayersPresenter
import com.littlefireflies.footballclub.presentation.ui.teamlist.TeamListContract
import com.littlefireflies.footballclub.presentation.ui.teamlist.TeamListPresenter
import com.littlefireflies.footballclub.utils.Constants
import com.littlefireflies.footballclub.utils.rx.AppSchedulerProvider
import com.littlefireflies.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private fun createRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                        .newBuilder()
                        .method(chain.request().method(), chain.request().body())
                        .build()
                chain.proceed(request)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    val builder = Retrofit.Builder()
    builder.client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())

    return builder.build()
}

private fun createNetworkService(retrofit: Retrofit): NetworkService {
    return retrofit.create(NetworkService::class.java)
}

val networkModule = module {
    single { createRetrofit() }
    single { createNetworkService(get()) }
}

val appModule = module {
    factory { CompositeDisposable() }
    factory { AppSchedulerProvider() as SchedulerProvider }
    factory<MatchRepository> { MatchDataStore(get(), androidContext()) }
    factory<TeamRepository> { TeamDataStore(get(), androidContext()) }
    factory<PlayerRepository> { PlayerDataStore(get()) }
    factory<LeagueRepository> { LeagueDataStore(get(), androidContext()) }

    factory { SplashPresenter<SplashContract.View>(get(), get(), get()) }
    factory { PreviousMatchPresenter<PreviousMatchContract.View>(get(), get(), get(), get()) }
    factory { NextMatchPresenter<NextMatchContract.View>(get(), get(), get(), get()) }
    factory { TeamListPresenter<TeamListContract.View>(get(), get(), get(), get()) }
    factory { FavoriteMatchPresenter<FavoriteMatchContract.View>(get(), get(), get()) }
    factory { FavoriteTeamPresenter<FavoriteTeamContract.View>(get(), get(), get()) }
    factory { SearchMatchPresenter<SearchMatchContract.View>(get(), get(), get()) }
    factory { MatchDetailPresenter<MatchDetailContract.View>(get(), get(), get(), get()) }
    factory { TeamDetailPresenter<TeamDetailContract.View>(get(), get(), get()) }
    factory { TeamPlayersPresenter<TeamPlayersContract.View>(get(), get(), get()) }
    factory { PlayerDetailPresenter<PlayerDetailContract.View>(get(), get(), get()) }
}