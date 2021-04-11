package com.picpay.picpaytest.features.users.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val userName: String = ""
): Parcelable