package com.navi.assignment.common

import okhttp3.Interceptor
import okhttp3.Response

class CustomHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = if (NetworkManager.isNetworkConnected) {
            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
        } else {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 2)
                .build()
        }

        return chain.proceed(request)
    }
}