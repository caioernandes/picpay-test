package com.picpay.picpaytest.repository.mappers

import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.model.UserResponse

fun List<UserResponse>.toUsers() = map { userResponse ->
    User(
        id = userResponse.id ?: 0,
        name = userResponse.name.orEmpty(),
        image = userResponse.image.orEmpty(),
        userName = userResponse.userName.orEmpty()
    )
}