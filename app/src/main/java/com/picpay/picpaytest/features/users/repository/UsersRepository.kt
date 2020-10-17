package com.picpay.picpaytest.features.users.repository

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.utils.Resource

interface UsersRepository {
   fun getUsers(): LiveData<Resource<List<User>>>
}