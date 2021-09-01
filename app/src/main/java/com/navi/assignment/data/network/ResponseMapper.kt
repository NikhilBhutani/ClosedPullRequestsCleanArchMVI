package com.navi.assignment.data.network


/*
* @param <T> the input type
* @param <D> the domain type
*/
interface ResponseMapper<D, T> {
    fun mapToDomain(type: T): D
}