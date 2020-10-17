package com.picpay.picpaytest.features.users.repository.service

import com.picpay.picpaytest.features.users.model.UserResponse
import com.picpay.picpaytest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET(Constants.GET_USERS)
    suspend fun getUsers(): Response<List<UserResponse>>
}