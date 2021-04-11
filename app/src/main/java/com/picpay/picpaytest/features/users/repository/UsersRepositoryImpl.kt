package com.picpay.picpaytest.features.users.repository

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.model.mappers.toUsers
import com.picpay.picpaytest.features.users.repository.dao.UserDao
import com.picpay.picpaytest.features.users.repository.service.UserRemoteDataSource
import com.picpay.picpaytest.utils.Resource
import com.picpay.picpaytest.utils.performGetOperation

class UsersRepositoryImpl constructor(
    private val userDao: UserDao,
    private val remoteDataSource: UserRemoteDataSource
) : UsersRepository {
    override fun getUsers(): LiveData<Resource<List<User>>> = performGetOperation(
        databaseQuery = { userDao.getFavoriteUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = {
            userDao.insertAllUsers(it.toUsers())
        }
    )
}