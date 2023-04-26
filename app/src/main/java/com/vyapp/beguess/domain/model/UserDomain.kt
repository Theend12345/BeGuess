package com.vyapp.beguess.domain.model

data class UserDomain(
    var username: String,
    var place: Long = 0,
    var score: Long = 0,
    var index: Long = 0,
    var status: Boolean = false
)

data class UserRegisterDomain(
    var username: String,
    var password: String,
    var place: Long = 0,
    var score: Long = 0,
    var status: Boolean = false
)