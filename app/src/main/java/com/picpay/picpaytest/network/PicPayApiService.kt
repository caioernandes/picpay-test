package com.picpay.picpaytest.network

import com.picpay.picpaytest.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface PicPayApiService {

    @GET("tests/mobdev/users")
    fun getUsers(): Observable<List<UserResponse>>
}