package com.navi.assignment.data.repository

import com.navi.assignment.data.mapper.PullRequestResponseMapper
import com.navi.assignment.data.network.PullRequestApi
import com.navi.assignment.domain.model.ClosedPullRequestDomainModel
import com.navi.assignment.domain.repository.PullRequestRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PullRequestRepositoryImpl @Inject constructor(
    private val pullRequestApi: PullRequestApi,
    private val pullRequestResponseMapper: PullRequestResponseMapper
) : PullRequestRepository {
    override fun getClosedPullRequests(): Single<List<ClosedPullRequestDomainModel>> {
        return pullRequestApi.getPullRequests().map(pullRequestResponseMapper::mapToDomain)
    }
}