package com.navi.assignment.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PullRequestApi {

    @GET("repos/square/retrofit/pulls?state=closed")
    fun getPullRequests(): Single<List<ClosedPullRequestResponse>>
}