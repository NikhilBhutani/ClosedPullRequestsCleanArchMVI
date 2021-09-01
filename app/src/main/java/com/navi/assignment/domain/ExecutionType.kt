package com.navi.assignment.domain

sealed class ExecutionType {
    object IO: ExecutionType()
    object Computation: ExecutionType()
}