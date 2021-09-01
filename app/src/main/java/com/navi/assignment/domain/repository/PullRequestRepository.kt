package com.navi.assignment.domain.repository

import com.navi.assignment.domain.model.ClosedPullRequestDomainModel
import io.reactivex.rxjava3.core.Single

interface PullRequestRepository {
    fun getClosedPullRequests(): Single<List<ClosedPullRequestDomainModel>>
}