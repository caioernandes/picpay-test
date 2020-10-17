package com.picpay.picpaytest.features.users.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.picpaytest.features.users.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getFavoriteUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<User>)
}