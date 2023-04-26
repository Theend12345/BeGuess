package com.vyapp.beguess.data.model

data class User(
    var username: String,
    var password: String,
    var place: Long = 0,
    var score: Long = 0,
    var status: Boolean = false
)