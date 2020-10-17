package com.picpay.picpaytest.features.users.repository.service

import com.picpay.picpaytest.utils.BaseDataSource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
): BaseDataSource() {

    suspend fun getUsers() = getResult { userService.getUsers() }
}