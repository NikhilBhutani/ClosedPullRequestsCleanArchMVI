package com.navi.assignment.data.mapper

import com.navi.assignment.data.network.ClosedPullRequestResponse
import com.navi.assignment.data.network.ResponseMapper
import com.navi.assignment.domain.model.ClosedPullRequestDomainModel
import javax.inject.Inject

class PullRequestResponseMapper @Inject constructor() :
    ResponseMapper<List<ClosedPullRequestDomainModel>, List<ClosedPullRequestResponse>> {

    override fun mapToDomain(
        closedPullRequestResponseList: List<ClosedPullRequestResponse>
    ): List<ClosedPullRequestDomainModel> {
        val closedPullRequestDomainModelList = mutableListOf<ClosedPullRequestDomainModel>()
        closedPullRequestResponseList.forEach { closedPullRequestResponse ->
            closedPullRequestDomainModelList.add(
                ClosedPullRequestDomainModel(
                    closedPullRequestResponse.title,
                    closedPullRequestResponse.createdAt,
                    closedPullRequestResponse.closedAt,
                    closedPullRequestResponse.user.userName,
                    closedPullRequestResponse.user.avatarUrl
                )
            )
        }
        return closedPullRequestDomainModelList
    }
}