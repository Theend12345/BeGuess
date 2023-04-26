package com.vyapp.beguess.data.network

import com.vyapp.beguess.data.model.Task
import com.vyapp.beguess.data.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BeguessApi {

    @POST("users/registrations")
    fun registration(@Body user: User): Observable<User>

    @POST("users/sightin")
    fun sightIn(@Body user: User): Observable<User>

    @GET("users/getall")
    fun getUsers(): Observable<List<User>>

    @GET("task/gettask")
    fun getTask(): Observable<Task>

    @POST("users/updauseranswer")
    fun rightAnswerState(@Body user: User): Observable<User>

    @POST("users/getUser")
    fun getUser(@Body user: User): Observable<User>

}