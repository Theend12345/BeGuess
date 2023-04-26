package com.vyapp.beguess.data.repository

import com.vyapp.beguess.data.network.BeguessApi
import com.vyapp.beguess.data.toDomainTask
import com.vyapp.beguess.data.toListUserDomain
import com.vyapp.beguess.data.toUser
import com.vyapp.beguess.data.toUserDomain
import com.vyapp.beguess.domain.model.TaskDomain
import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.model.UserRegisterDomain
import com.vyapp.beguess.domain.repository.BeguessRepository
import io.reactivex.Observable

class BeguessRepositoryImp(val api: BeguessApi): BeguessRepository {

    override fun register(user: UserRegisterDomain): Observable<UserDomain> {
       return api.registration(user.toUser()).map { it.toUserDomain() }
    }

    override fun sightIn(user: UserRegisterDomain): Observable<UserDomain> {
        return api.sightIn(user.toUser()).map { it.toUserDomain() }
    }

    override fun getUsers(): Observable<List<UserDomain>> {
        return  api.getUsers().map { it.toListUserDomain() }
    }

    override fun getTask(): Observable<TaskDomain> {
        return api.getTask().map { it.toDomainTask() }
    }

    override fun rightAnswer(user: UserDomain): Observable<UserDomain> {
       return api.rightAnswerState(user.toUser()).map { it.toUserDomain() }
    }

    override fun getUser(user: UserDomain): Observable<UserDomain> {
        return api.getUser(user.toUser()).map { it.toUserDomain() }
    }
}