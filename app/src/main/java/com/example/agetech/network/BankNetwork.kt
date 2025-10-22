package com.example.agetech.network

import com.example.agetech.model.Login
import com.example.agetech.model.Payments
import com.example.agetech.repository.BankRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface BankServices {
    @GET("Login")
    suspend fun getLogin(): List<Login>

    @GET("payments")
    suspend fun getPayments(): List<Payments>
    companion object{
        private val api = "https://60bd336db8ab3700175a03b3.mockapi.io/treinamento/"

        fun create():BankServices {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .baseUrl(api)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BankServices::class.java)
        }

    }
}






