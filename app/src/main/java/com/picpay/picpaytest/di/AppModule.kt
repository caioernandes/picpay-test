package com.picpay.picpaytest.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.picpaytest.db.AppDatabase
import com.picpay.picpaytest.db.CreditCardDao
import com.picpay.picpaytest.db.UserDao
import com.picpay.picpaytest.network.UserRemoteDataSource
import com.picpay.picpaytest.network.UserService
import com.picpay.picpaytest.repository.creditcard.CreditCardRepository
import com.picpay.picpaytest.repository.creditcard.CreditCardRepositoryImpl
import com.picpay.picpaytest.repository.users.UsersRepository
import com.picpay.picpaytest.repository.users.UsersRepositoryImpl
import com.picpay.picpaytest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userService: UserService
    ) = UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.usersDao()

    @Singleton
    @Provides
    fun provideCreditCardDao(db: AppDatabase) = db.creditCardDao()

    @Singleton
    @Provides
    fun provideRepositoryUser(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserDao
    ): UsersRepository = UsersRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideRepositoryCreditCard(
        localDataSource: CreditCardDao
    ): CreditCardRepository = CreditCardRepositoryImpl(localDataSource)
}