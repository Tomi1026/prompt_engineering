package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("id"           ) var id          : String?            = null,
    @SerializedName("object"       ) var _object      : String?            = null,
    @SerializedName("created_at"   ) var createdAt   : Int?               = null,
    @SerializedName("assistant_id" ) var assistantId : String?            = null,
    @SerializedName("thread_id"    ) var threadId    : String?            = null,
    @SerializedName("run_id"       ) var runId       : String?            = null,
    @SerializedName("role"         ) var role        : String?            = null,
    @SerializedName("content"      ) var content     : ArrayList<Content> = arrayListOf(),
    @SerializedName("attachments"  ) var attachments : ArrayList<String>  = arrayListOf(),
    @SerializedName("metadata"     ) var metadata    : Metadata?          = Metadata()
)
