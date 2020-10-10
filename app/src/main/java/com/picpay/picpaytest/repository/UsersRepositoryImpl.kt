package com.picpay.picpaytest.repository

import com.picpay.picpaytest.db.UsersDao
import com.picpay.picpaytest.network.PicPayApiService
import com.picpay.picpaytest.model.User

class UsersRepositoryImpl(
    private val usersDao: UsersDao,
    private val service: PicPayApiService
) : UsersRepository {
    override fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }
}