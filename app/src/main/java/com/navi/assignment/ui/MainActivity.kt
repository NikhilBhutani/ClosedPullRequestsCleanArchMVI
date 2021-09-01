package com.navi.assignment.ui

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.navi.assignment.R
import com.navi.assignment.base.BaseActivity
import com.navi.assignment.base.BaseView
import com.navi.assignment.presentation.MainViewModel
import com.navi.assignment.presentation.PullRequestIntent
import com.navi.assignment.presentation.PullRequestIntent.LoadClosedPullRequestIntent
import com.navi.assignment.presentation.PullRequestViewEffect
import com.navi.assignment.presentation.PullRequestViewState
import com.navi.assignment.ui.customviews.BallBeatIndicator
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(),
    BaseView<PullRequestIntent, PullRequestViewState, PullRequestViewEffect> {

    override val _intents: PublishSubject<PullRequestIntent> = PublishSubject.create()
    override val intents: Observable<PullRequestIntent> = _intents.hide()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var closedPullRequestAdapter: ClosedPullRequestAdapter

    override val clazz: Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId() = R.layout.activity_main

    override fun initView() {
        setupRecyclerView()

    }

    override fun bind() {
        vm.getStates().subscribe(this::render)
        vm.getViewEffects().subscribe(this::viewEffects)
        vm.processIntents(intents())
    }

    override fun intents(): Observable<PullRequestIntent> {
        return pullRequestIntentMerge(intents)
    }

    override fun onStart() {
        super.onStart()
        _intents.onNext(LoadClosedPullRequestIntent)
    }

    override fun render(state: PullRequestViewState) {

        with(state) {
            if (inProgress) {
                indicator.indicator = BallBeatIndicator()
                indicator.visibility = View.VISIBLE
                indicator.show()
            } else {
                indicator.hide()
            }

            if (inProgress.not() && closedPullRequestViewDataList.isNotEmpty()) {
                pull_request_rv.visibility = View.VISIBLE
                closedPullRequestAdapter.addData(state.closedPullRequestViewDataList)
            }
        }
    }

    override fun viewEffects(effect: PullRequestViewEffect) {

    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        closedPullRequestAdapter = ClosedPullRequestAdapter()
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.rv_divider)
            ?.let { itemDecorator.setDrawable(it) }

        with(pull_request_rv) {
            layoutManager = linearLayoutManager
            adapter = closedPullRequestAdapter
            addItemDecoration(itemDecorator)
        }
    }

    private fun pullRequestIntentMerge(
        shared: Observable<PullRequestIntent>
    ): Observable<PullRequestIntent> {
        return Observable.merge(
            listOf(
                shared.ofType(LoadClosedPullRequestIntent::class.java)
            )
        )
    }
}