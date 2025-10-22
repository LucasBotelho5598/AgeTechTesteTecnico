package com.example.agetech.dao

import androidx.room.Dao
import com.example.agetech.model.Payments
import retrofit2.http.Query

@Dao
interface PaymentsDao {

    fun getPayments(): List<Payments>
}