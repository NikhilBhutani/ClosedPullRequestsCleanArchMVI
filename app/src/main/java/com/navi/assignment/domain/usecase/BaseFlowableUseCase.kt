package com.navi.assignment.domain.usecase

import com.navi.assignment.domain.ExecutionType
import com.navi.assignment.domain.ExecutionType.IO
import com.navi.assignment.domain.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Flowable

abstract class BaseFlowableUseCase<T, in Param> constructor(
    private val schedulerProvider: SchedulerProvider
) {

    protected abstract fun buildUseCase(param: Param?): Flowable<T>

    open fun execute(param: Param?=null, executionType: ExecutionType = IO): Flowable<T> {
        return buildUseCase(param)
            .subscribeOn(
                if (executionType is IO) {
                    schedulerProvider.io()
                } else {
                    schedulerProvider.computation()
                }
            )
            .observeOn(schedulerProvider.ui())
    }
}