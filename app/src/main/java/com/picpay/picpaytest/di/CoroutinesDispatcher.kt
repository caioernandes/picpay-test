package com.picpay.picpaytest.di;

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesDispatcher(
    val Main: CoroutineDispatcher = Dispatchers.Main,
    val IO: CoroutineDispatcher = Dispatchers.IO
)