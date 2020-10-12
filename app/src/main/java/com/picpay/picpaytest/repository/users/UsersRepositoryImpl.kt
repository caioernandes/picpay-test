package com.picpay.picpaytest.repository.users

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.db.UserDao
import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.network.UserRemoteDataSource
import com.picpay.picpaytest.model.mappers.toUsers
import com.picpay.picpaytest.utils.Resource
import com.picpay.picpaytest.utils.performGetOperation
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val remoteDataSource: UserRemoteDataSource
) : UsersRepository {
    override fun getUsers(): LiveData<Resource<List<User>>> = performGetOperation(
        databaseQuery = { userDao.getFavoriteUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { userDao.insertAllUsers(it.toUsers()) }
    )
}