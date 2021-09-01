package com.navi.assignment.presentation

import com.navi.assignment.base.BaseAction

sealed class PullRequestAction : BaseAction {
     object LoadClosedPullRequestAction: PullRequestAction()
}
