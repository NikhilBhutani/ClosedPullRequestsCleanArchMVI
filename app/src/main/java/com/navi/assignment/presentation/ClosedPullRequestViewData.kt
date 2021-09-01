package com.navi.assignment.presentation

data class ClosedPullRequestViewData(
    val title: String,
    val createdAt: String,
    val closedAt: String,
    val userName: String,
    val avatarUrl: String,
)