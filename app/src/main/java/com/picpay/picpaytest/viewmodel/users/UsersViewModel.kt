package com.picpay.picpaytest.viewmodel.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.repository.users.UsersRepository
import com.picpay.picpaytest.utils.Resource

class UsersViewModel @ViewModelInject constructor(
    repository: UsersRepository
) : ViewModel() {
    val users: LiveData<Resource<List<User>>> = repository.getUsers()
}