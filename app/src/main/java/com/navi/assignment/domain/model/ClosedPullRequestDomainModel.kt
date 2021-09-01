package com.navi.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class ClosedPullRequestDomainModel(
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("closed_at") val closedAt: String,
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)
