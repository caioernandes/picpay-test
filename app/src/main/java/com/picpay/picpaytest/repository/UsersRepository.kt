package com.picpay.picpaytest.repository

import com.picpay.picpaytest.model.User

interface UsersRepository {
    fun getUsers(): List<User>
}