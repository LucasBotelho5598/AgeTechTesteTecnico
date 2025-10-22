package com.example.agetech.di

import com.example.agetech.network.BankServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule{
    @Singleton
    @Provides
    fun provideBankServices(): BankServices {
        return BankServices.create()
    }


}