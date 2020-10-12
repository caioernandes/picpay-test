package com.picpay.picpaytest.repository.users

import androidx.lifecycle.LiveData
import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.utils.Resource

interface UsersRepository {
   fun getUsers(): LiveData<Resource<List<User>>>
}