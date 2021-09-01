package com.navi.assignment.presentation.mapper

import com.navi.assignment.domain.model.ClosedPullRequestDomainModel
import com.navi.assignment.presentation.ClosedPullRequestViewData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ClosedPullRequestViewDataMapper @Inject constructor():
    ViewMapper<List<ClosedPullRequestViewData>, List<ClosedPullRequestDomainModel>> {
    override fun mapToView(type: List<ClosedPullRequestDomainModel>): List<ClosedPullRequestViewData> {
        val closedPullRequestViewDataList = mutableListOf<ClosedPullRequestViewData>()
        type.forEach { closedPullRequestDomainModel ->

            closedPullRequestViewDataList.add(
                ClosedPullRequestViewData(
                    title = closedPullRequestDomainModel.title,
                    createdAt = getDate(closedPullRequestDomainModel.createdAt),
                    closedAt = getDate(closedPullRequestDomainModel.closedAt),
                    userName = closedPullRequestDomainModel.userName,
                    avatarUrl = closedPullRequestDomainModel.avatarUrl
                )
            )
        }

        return closedPullRequestViewDataList
    }

    private fun getDate(dateString: String): String{
      val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
      val viewDateFormat = SimpleDateFormat("dd/MM/yyyy, HH:mm")

        return viewDateFormat.format(dateFormat.parse(dateString))
    }
}