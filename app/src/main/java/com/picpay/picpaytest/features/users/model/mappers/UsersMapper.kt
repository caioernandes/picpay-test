package com.picpay.picpaytest.features.users.model.mappers

import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.model.UserResponse

fun List<UserResponse>.toUsers() = map { userResponse ->
    User(
        id = userResponse.id ?: 0,
        name = userResponse.name.orEmpty(),
        image = userResponse.image.orEmpty(),
        userName = userResponse.userName.orEmpty()
    )
}