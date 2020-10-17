package com.picpay.picpaytest.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.picpaytest.db.AppDatabase
import com.picpay.picpaytest.features.creditcard.repository.dao.CreditCardDao
import com.picpay.picpaytest.features.users.repository.dao.UserDao
import com.picpay.picpaytest.features.users.repository.service.UserRemoteDataSource
import com.picpay.picpaytest.features.users.repository.service.UserService
import com.picpay.picpaytest.features.creditcard.repository.CreditCardRepository
import com.picpay.picpaytest.features.creditcard.repository.CreditCardRepositoryImpl
import com.picpay.picpaytest.features.payment.repository.PaymentRepository
import com.picpay.picpaytest.features.payment.repository.PaymentRepositoryImpl
import com.picpay.picpaytest.features.payment.repository.service.PaymentRemoteDataSource
import com.picpay.picpaytest.features.payment.repository.service.PaymentService
import com.picpay.picpaytest.features.users.repository.UsersRepository
import com.picpay.picpaytest.features.users.repository.UsersRepositoryImpl
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

    @Provides
    @Singleton
    fun providePaymentService(retrofit: Retrofit): PaymentService =
        retrofit.create(PaymentService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userService: UserService
    ): UserRemoteDataSource = UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun providePaymentRemoteDataSource(
        paymentService: PaymentService
    ): PaymentRemoteDataSource = PaymentRemoteDataSource(paymentService)

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase = AppDatabase.getDatabase(appContext)

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

    @Singleton
    @Provides
    fun providePaymentRepository(
        remoteDataSource: PaymentRemoteDataSource
    ): PaymentRepository = PaymentRepositoryImpl(remoteDataSource)
}