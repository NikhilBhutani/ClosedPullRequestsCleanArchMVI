package com.navi.assignment.base

import io.reactivex.rxjava3.core.FlowableTransformer

interface BaseActionProcessor<A: BaseAction, R: BaseResult> {
    fun transformFromAction(): FlowableTransformer<A, R>
}