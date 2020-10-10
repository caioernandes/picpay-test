package com.picpay.picpaytest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.picpaytest.model.User

@Dao
interface UsersDao {

    @Insert
    fun insertUser(user: User)

    @Query("DELETE FROM user_table WHERE name = :userName")
    fun deleteUser(userName: String?)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table")
    fun getFavoriteUser(): LiveData<List<User>>
}