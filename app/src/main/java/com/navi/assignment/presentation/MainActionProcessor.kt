package com.navi.assignment.presentation

import com.navi.assignment.base.BaseActionProcessor
import com.navi.assignment.domain.ExecutionType
import com.navi.assignment.domain.usecase.GetClosedPullRequestUseCase
import com.navi.assignment.presentation.PullRequestAction.LoadClosedPullRequestAction
import com.navi.assignment.presentation.PullRequestResult.GetPRsResult
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import javax.inject.Inject

class MainActionProcessor @Inject constructor(
    private val getClosedPullRequestUseCase: GetClosedPullRequestUseCase
) : BaseActionProcessor<PullRequestAction, PullRequestResult> {

    override fun transformFromAction(): FlowableTransformer<PullRequestAction, PullRequestResult> {
        return FlowableTransformer { actionObservable ->
            actionObservable.publish {
                Flowable.merge(
                    it.ofType(LoadClosedPullRequestAction::class.java).compose(getClosedPRs()),
                    Flowable.never()
                )
            }

        }
    }

    private fun getClosedPRs():
            FlowableTransformer<LoadClosedPullRequestAction, PullRequestResult> {
        return FlowableTransformer {
            it.flatMap {
                getClosedPullRequestUseCase
                    .execute(executionType = ExecutionType.IO)
                    .map { closedPullRequestDomainModel ->
                        GetPRsResult(closedPullRequestDomainModel)
                    }.cast(PullRequestResult::class.java)
            }.onErrorReturn {
                PullRequestResult.Failure(it.localizedMessage)
            }.startWithItem(PullRequestResult.InProgress)
        }
    }
}