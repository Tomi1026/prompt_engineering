package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class AssistantRunRequest(
    @SerializedName("assistant_id" ) var assistantId : String? = null
)
