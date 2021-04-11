package com.picpay.picpaytest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.picpaytest.BuildConfig
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.repository.dao.UserDao

@Database(
    entities = [User::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, BuildConfig.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}