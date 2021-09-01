package com.navi.assignment.domain.usecase

import com.navi.assignment.domain.model.ClosedPullRequestDomainModel
import com.navi.assignment.domain.repository.PullRequestRepository
import com.navi.assignment.domain.schedulers.SchedulerProvider
import com.navi.assignment.domain.usecase.GetClosedPullRequestUseCase.Param
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetClosedPullRequestUseCase @Inject constructor(
    private val pullRequestRepository: PullRequestRepository,
    schedulerProvider: SchedulerProvider
): BaseFlowableUseCase<List<ClosedPullRequestDomainModel>, Param>(schedulerProvider) {

    object Param

    override fun buildUseCase(param: Param?): Flowable<List<ClosedPullRequestDomainModel>> {
        return pullRequestRepository.getClosedPullRequests().toFlowable()
    }
}