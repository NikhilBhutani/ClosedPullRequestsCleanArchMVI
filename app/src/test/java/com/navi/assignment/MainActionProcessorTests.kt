//package com.navi.assignment
//
//import com.navi.assignment.domain.usecase.GetClosedPullRequestUseCase
//import com.navi.assignment.presentation.MainActionProcessor
//import com.navi.assignment.presentation.PullRequestAction
//import com.navi.assignment.presentation.PullRequestResult
//import io.reactivex.rxjava3.core.Flowable
//import io.reactivex.rxjava3.core.FlowableTransformer
//import org.junit.Before
//import org.junit.Test
//import java.util.*
//
//class MainActionProcessorTests {
//
//    @Mock
//    lateinit var getClosedPullRequestUseCase: GetClosedPullRequestUseCase
//
//
//    @Before
//    fun setup(){
//    }
//
//    @Test
//    fun `should return the GetPRsResult when LoadClosedPullRequestAction is passed`(){
//
//        val inputAction = PullRequestAction.LoadClosedPullRequestAction
//        val outAction = PullRequestResult.GetPRsResult(emptyList())
//
//        val mainActionProcessor = MainActionProcessor(getClosedPullRequestUseCase)
//
//        Flowable.just(inputAction).compose(
//            mainActionProcessor.transformFromAction()
//        ).test()
//            .assertNoValues()
//            .assertValueCount(1)
//            .assertValue {
//                it == outAction
//            }
//    }
//}