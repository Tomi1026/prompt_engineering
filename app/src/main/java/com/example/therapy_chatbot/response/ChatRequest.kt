package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class ChatRequest (
    @SerializedName("instructions" ) var instructions : String?,
    @SerializedName("name") var name: String?,
    @SerializedName("tools" ) var tools : ArrayList<Tools>,
    @SerializedName("model") var mode: String?
)
