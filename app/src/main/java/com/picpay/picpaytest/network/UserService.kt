package com.picpay.picpaytest.network

import com.picpay.picpaytest.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("tests/mobdev/users")
    suspend fun getUsers(): Response<List<UserResponse>>
}