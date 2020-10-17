package com.picpay.picpaytest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.picpaytest.BuildConfig
import com.picpay.picpaytest.features.creditcard.repository.dao.CreditCardDao
import com.picpay.picpaytest.features.users.repository.dao.UserDao
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.users.model.User

@Database(
    entities = [User::class, CreditCard::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao
    abstract fun creditCardDao(): CreditCardDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "picpay_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}