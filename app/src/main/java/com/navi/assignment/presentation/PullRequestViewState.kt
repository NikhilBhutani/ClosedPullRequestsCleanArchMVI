package com.navi.assignment.presentation

import com.navi.assignment.base.BaseViewState

data class PullRequestViewState(
    val closedPullRequestViewDataList: List<ClosedPullRequestViewData>,
    val inProgress: Boolean = false,
    val isLoadingMore:Boolean = false,
    val isError: Boolean = false,
    val error: Error
) : BaseViewState {
    data class Error(
        val error: String
    )

    companion object {
        fun default() = PullRequestViewState(
            closedPullRequestViewDataList = emptyList(),
            inProgress = false,
            isLoadingMore = false,
            isError = false,
            error = Error("Pull Request Error..")
        )
    }
}
