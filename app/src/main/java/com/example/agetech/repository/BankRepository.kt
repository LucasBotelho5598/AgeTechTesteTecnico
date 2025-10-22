package com.example.agetech.repository

import com.example.agetech.model.Login
import com.example.agetech.model.Payments
import com.example.agetech.network.BankServices
import jakarta.inject.Inject
import jakarta.inject.Singleton


@Singleton
class BankRepository @Inject constructor(
    private val bankServices: BankServices
){
     suspend fun getLogin(): List<Login> = bankServices.getLogin()
     suspend fun getPayments(): List<Payments> = bankServices.getPayments()

}