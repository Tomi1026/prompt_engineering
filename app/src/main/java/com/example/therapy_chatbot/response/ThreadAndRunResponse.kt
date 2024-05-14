package com.example.therapy_chatbot.response

import com.google.gson.annotations.SerializedName

data class RunResponse(
    @SerializedName("id"                    ) var id                  : String?             = null,
    @SerializedName("object"                ) var _object              : String?             = null,
    @SerializedName("created_at"            ) var createdAt           : Int?                = null,
    @SerializedName("assistant_id"          ) var assistantId         : String?             = null,
    @SerializedName("thread_id"             ) var threadId            : String?             = null,
    @SerializedName("status"                ) var status              : String?             = null,
    @SerializedName("started_at"            ) var startedAt           : String?             = null,
    @SerializedName("expires_at"            ) var expiresAt           : Int?                = null,
    @SerializedName("cancelled_at"          ) var cancelledAt         : String?             = null,
    @SerializedName("failed_at"             ) var failedAt            : String?             = null,
    @SerializedName("completed_at"          ) var completedAt         : String?             = null,
    @SerializedName("required_action"       ) var requiredAction      : String?             = null,
    @SerializedName("last_error"            ) var lastError           : String?             = null,
    @SerializedName("model"                 ) var model               : String?             = null,
    @SerializedName("instructions"          ) var instructions        : String?             = null,
    @SerializedName("tools"                 ) var tools               : ArrayList<String>   = arrayListOf(),
    @SerializedName("tool_resources"        ) var toolResources       : ToolResources?      = ToolResources(),
    @SerializedName("metadata"              ) var metadata            : Metadata?           = Metadata(),
    @SerializedName("temperature"           ) var temperature         : Float?              = null,
    @SerializedName("top_p"                 ) var topP                : Float?              = null,
    @SerializedName("max_completion_tokens" ) var maxCompletionTokens : String?             = null,
    @SerializedName("max_prompt_tokens"     ) var maxPromptTokens     : String?             = null,
    @SerializedName("truncation_strategy"   ) var truncationStrategy  : TruncationStrategy? = TruncationStrategy(),
    @SerializedName("incomplete_details"    ) var incompleteDetails   : String?             = null,
    @SerializedName("usage"                 ) var usage               : Usage?              = null,
    @SerializedName("response_format"       ) var responseFormat      : String?             = null,
    @SerializedName("tool_choice"           ) var toolChoice          : String?             = null
)

