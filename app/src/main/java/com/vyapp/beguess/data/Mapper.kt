package com.vyapp.beguess.data

import com.vyapp.beguess.data.model.Task
import com.vyapp.beguess.data.model.User
import com.vyapp.beguess.domain.model.TaskDomain
import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.model.UserRegisterDomain

fun User.toUserDomain() = UserDomain(this.username, this.place, this.score, 0, this.status)
fun UserRegisterDomain.toUser() =
    User(this.username, this.password, this.place, this.score, this.status)

fun List<User>.toListUserDomain(): List<UserDomain>{

    val users: MutableList<UserDomain> = mutableListOf()
    for(i in this){
        users.add(i.toUserDomain())
    }
    return users

}

fun Task.toDomainTask() = TaskDomain(this.res,this.word,this.description)
fun UserDomain.toUser() = User(this.username,this.username,this.place,this.score,this.status)