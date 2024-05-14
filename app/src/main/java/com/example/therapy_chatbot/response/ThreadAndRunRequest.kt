package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class ThreadAndRunRequest (
    @SerializedName("assistant_id" ) var assistantId : String? = null,
    @SerializedName("thread") var thread: Thread? = Thread()
)

