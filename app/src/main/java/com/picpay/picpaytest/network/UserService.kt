package com.picpay.picpaytest.network

import com.picpay.picpaytest.model.UserResponse
import com.picpay.picpaytest.utils.Constants.GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET(GET_USERS)
    suspend fun getUsers(): Response<List<UserResponse>>
}