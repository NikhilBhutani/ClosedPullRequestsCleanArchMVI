package com.navi.assignment.data.network

import com.google.gson.annotations.SerializedName

data class ClosedPullRequestResponse(
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("closed_at") val closedAt: String,
    @SerializedName("user") val user: User
) {
    data class User(
        @SerializedName("login") val userName: String,
        @SerializedName("avatar_url") val avatarUrl: String,
    )
}
