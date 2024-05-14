package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class ThreadMessagesResponse(
    @SerializedName("object"   ) var _object  : String?         = null,
    @SerializedName("data"     ) var data    : ArrayList<MessageResponse> = arrayListOf(),
    @SerializedName("first_id" ) var firstId : String?         = null,
    @SerializedName("last_id"  ) var lastId  : String?         = null,
    @SerializedName("has_more" ) var hasMore : Boolean?        = null
)
