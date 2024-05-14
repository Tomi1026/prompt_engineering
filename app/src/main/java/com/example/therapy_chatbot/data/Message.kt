package com.example.therapy_chatbot.data

data class Message(
    val senderIsMe: Boolean,
    val content: String,
    val time: String
)
