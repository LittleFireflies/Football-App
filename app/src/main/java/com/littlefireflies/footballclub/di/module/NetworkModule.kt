package com.littlefireflies.footballclub.di.module

import com.littlefireflies.footballclub.BuildConfig
import com.littlefireflies.footballclub.data.network.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by widyarso.purnomo on 03/09/2018.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
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
    }

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(okHttpClient)
                .baseUrl("https://www.thesportsdb.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}