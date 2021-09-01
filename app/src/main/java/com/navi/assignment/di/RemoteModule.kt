package com.navi.assignment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.navi.assignment.application.App
import com.navi.assignment.common.RemoteConstants
import com.navi.assignment.data.network.PullRequestApi
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    @Named("AppGson")
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(app: App): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @Named("AppRetrofit")
    fun providesRetrofit(
        @Named("AppGson") gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RemoteConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesPullRequestApi(
        @Named("AppRetrofit") retrofit: Retrofit
    ): PullRequestApi {
        return retrofit.create(PullRequestApi::class.java)
    }
}