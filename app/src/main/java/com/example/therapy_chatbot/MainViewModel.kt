package com.example.therapy_chatbot

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

class MainViewModel : ViewModel()
{
    var messageInput= ""
    val messages= mutableStateOf<List<Message>>(mutableListOf(
        Message(true,"asd","21:00"),
        Message(false,"asd","21:00"),
        Message(true,"asAGFHSSSSSSSSSSSSSSSSSSJSKZUEIWd","21:00"),
        Message(false,"asd","21:00"),
        Message(false,"akljfdhzgioueszrojpcxngvké.jyhgerpéof9uiwáőpfjsd","21:00"),
        Message(false,"asselihféosigápyjrepoigzőörgődod","21:00"),
        Message(false,"asselihféosigápyjrepoigzőörgődod","21:00"),
        Message(false,"asselihféosigápyjrepoigzőörgődod","21:00"),
        Message(false,"asselihféosigápyjrepoigzőörgődod","21:00"),
        Message(false,"asselihféosigápyjrepoigzőörgődod","21:00")
    ))
    fun sendMessageToAssistant(message: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ChatGptService::class.java)
        val request = Messages(role= "user", content = message)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                NetworkRepository.addMessageAndRunAssistant(message)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}