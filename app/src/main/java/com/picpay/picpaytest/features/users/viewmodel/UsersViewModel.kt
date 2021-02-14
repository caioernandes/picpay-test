package com.picpay.picpaytest.features.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.repository.UsersRepository
import com.picpay.picpaytest.utils.Resource

class UsersViewModel constructor(
    repository: UsersRepository
) : ViewModel() {
    val users: LiveData<Resource<List<User>>> = repository.getUsers()
}