package com.example.therapy_chatbot.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    @Volatile
    var INSTANCE : ChatGptService? = null



    fun getInstance() : ChatGptService{
        synchronized(this){
            // Create the OkHttp client with logging interceptor
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Set log level as desired
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return INSTANCE ?: Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChatGptService::class.java)
                .also {
                    INSTANCE = it
                }

        }
    }
}