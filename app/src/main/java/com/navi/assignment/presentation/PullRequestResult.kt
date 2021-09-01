package com.navi.assignment.presentation

import com.navi.assignment.base.BaseResult
import com.navi.assignment.domain.model.ClosedPullRequestDomainModel

sealed class PullRequestResult : BaseResult {

    data class GetPRsResult(
        val closedPullRequestDomainModel: List<ClosedPullRequestDomainModel>
    ) : PullRequestResult()

    object InProgress : PullRequestResult()

    data class Failure(
        val errorMessage: String
    ) : PullRequestResult()

}
