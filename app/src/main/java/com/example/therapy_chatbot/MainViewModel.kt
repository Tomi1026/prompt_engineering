package com.example.therapy_chatbot

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.therapy_chatbot.data.Message
import com.example.therapy_chatbot.network.ChatGptService
import com.example.therapy_chatbot.network.NetworkRepository
import com.example.therapy_chatbot.response.Messages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel()
{
    var messageInput= ""
    val messages= mutableStateOf<List<Message>>(mutableListOf(
        Message(false,"Hello, how can I help you?", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))),

    ))
    suspend fun sendMessageToAssistant(message: String) : String {
        return suspendCoroutine { continuation ->
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ChatGptService::class.java)
            val request = Messages(role= "user", content = message)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val answer = NetworkRepository.addMessageAndRunAssistant(message)
                    Log.d("answer", answer)
                    continuation.resume(answer)
                } catch (e: Exception) {
                    // Handle exception
                    continuation.resumeWithException(e)
                }
            }
        }
    }
}