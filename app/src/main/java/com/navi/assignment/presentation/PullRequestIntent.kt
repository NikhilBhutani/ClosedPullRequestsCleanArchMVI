package com.navi.assignment.presentation

import com.navi.assignment.base.BaseIntent

sealed class PullRequestIntent : BaseIntent {
    object LoadClosedPullRequestIntent: PullRequestIntent()
}