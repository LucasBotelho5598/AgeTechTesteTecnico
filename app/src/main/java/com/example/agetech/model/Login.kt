package com.example.agetech.model

import kotlinx.serialization.Serializable




data class Login(
    val customerName: String,
    val accountNumber: String,
    val branchNumber: String,
    val checkingAccountBalance: Int,
    val id: String

)
