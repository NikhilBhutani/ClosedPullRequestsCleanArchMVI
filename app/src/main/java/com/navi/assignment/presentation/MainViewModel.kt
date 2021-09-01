package com.navi.assignment.presentation

import com.navi.assignment.base.BaseViewModel
import com.navi.assignment.presentation.PullRequestAction.LoadClosedPullRequestAction
import com.navi.assignment.presentation.PullRequestIntent.LoadClosedPullRequestIntent
import com.navi.assignment.presentation.PullRequestResult.GetPRsResult
import com.navi.assignment.presentation.mapper.ClosedPullRequestViewDataMapper
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

class MainViewModel @Inject constructor(
    override val actionProcessor: MainActionProcessor,
    private val closedPullRequestViewDataMapper: ClosedPullRequestViewDataMapper
) : BaseViewModel<PullRequestIntent, PullRequestAction, PullRequestResult, PullRequestViewState, PullRequestViewEffect>() {
    override fun intentFilter(): FlowableTransformer<PullRequestIntent, PullRequestIntent> {
        return FlowableTransformer { intent ->
            intent.publish { shared ->
                Flowable.merge(
                    shared.ofType(LoadClosedPullRequestIntent::class.java),
                    Flowable.never()
                )
            }
        }
    }

    override fun actionFromIntent(intent: PullRequestIntent): PullRequestAction {
        return when (intent) {
            is LoadClosedPullRequestIntent -> {
                LoadClosedPullRequestAction
            }
        }
    }

    override fun reducer(): BiFunction<PullRequestViewState, PullRequestResult, PullRequestViewState> {
        return BiFunction { previousState, result ->
            when (result) {
                is GetPRsResult -> {
                    previousState.copy(
                        closedPullRequestViewDataList = closedPullRequestViewDataMapper.mapToView(
                            result.closedPullRequestDomainModel
                        ),
                        inProgress = false,
                        isError = false,
                        error = PullRequestViewState.Error("")
                    )
                }
                is PullRequestResult.Failure -> previousState
                is PullRequestResult.InProgress -> previousState.copy(
                    closedPullRequestViewDataList = emptyList(),
                    inProgress = true,
                    isLoadingMore = false,
                    isError = false,
                    error = PullRequestViewState.Error("")
                )
            }
        }
    }

    override fun initialState(): PullRequestViewState {
        return PullRequestViewState.default()
    }

    override fun viewEffect(): Flowable<PullRequestViewEffect> {
        return Flowable.never()
    }

    override fun provisionViewEffect(): Consumer<in PullRequestResult>? {
        return Consumer {
            when(it){
                is GetPRsResult -> {

                }
                is PullRequestResult.Failure -> {}
                PullRequestResult.InProgress -> {

                }
            }
        }
    }
}