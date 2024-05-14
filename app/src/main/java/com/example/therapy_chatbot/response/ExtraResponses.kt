package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

class ExtraResponses {
}

data class Tools (

    @SerializedName("type" ) var type : String? = null

)

data class Thread (
    @SerializedName("messages" ) var messages : ArrayList<Messages> = arrayListOf()
)

data class Messages (
    @SerializedName("role") var role: String? = null,
    @SerializedName("content" ) var content : String? = null
)

data class ToolResources (

    @SerializedName("code_interpreter" ) var codeInterpreter : CodeInterpreter? = CodeInterpreter()

)

data class CodeInterpreter (

    @SerializedName("file_ids" ) var fileIds : ArrayList<String> = arrayListOf()

)


data class Metadata (

    @SerializedName("user_id" ) var userId : String? = null

)

data class TruncationStrategy (

    @SerializedName("type"          ) var type         : String? = null,
    @SerializedName("last_messages" ) var lastMessages : String? = null

)


data class Text (

    @SerializedName("value"       ) var value       : String?           = null,
    @SerializedName("annotations" ) var annotations : ArrayList<String> = arrayListOf()

)


data class Content (

    @SerializedName("type" ) var type : String? = null,
    @SerializedName("text" ) var text : Text?   = Text()

)

data class Usage(
    @SerializedName("prompt_tokens"     ) var promptTokens     : Int? = null,
    @SerializedName("completion_tokens" ) var completionTokens : Int? = null,
    @SerializedName("total_tokens"      ) var totalTokens      : Int? = null
)