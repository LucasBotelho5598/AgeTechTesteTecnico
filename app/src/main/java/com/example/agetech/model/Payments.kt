package com.example.agetech.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "Payments")
data class Payments(
    val paymentDate: String,
    val electricityBill: String,
    @PrimaryKey(autoGenerate = true)
    val id: String 
)
