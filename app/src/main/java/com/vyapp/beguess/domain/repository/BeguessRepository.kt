package com.vyapp.beguess.domain.repository

import com.vyapp.beguess.domain.model.TaskDomain
import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.model.UserRegisterDomain
import io.reactivex.Observable

interface BeguessRepository {

    fun register(user: UserRegisterDomain): Observable<UserDomain>
    fun sightIn(user: UserRegisterDomain): Observable<UserDomain>
    fun getUsers(): Observable<List<UserDomain>>
    fun getTask(): Observable<TaskDomain>
    fun rightAnswer(user: UserDomain): Observable<UserDomain>
    fun getUser(user: UserDomain): Observable<UserDomain>

}