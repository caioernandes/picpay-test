package com.picpay.picpaytest.features.users.repository.service

import com.picpay.picpaytest.utils.BaseDataSource

class UserRemoteDataSource constructor(
    private val userService: UserService
): BaseDataSource() {

    suspend fun getUsers() = getResult { userService.getUsers() }
}