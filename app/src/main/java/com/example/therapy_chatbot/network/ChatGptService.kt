package com.example.therapy_chatbot.network

import com.example.therapy_chatbot.response.AssistantRunRequest
import com.example.therapy_chatbot.response.MessageResponse
import com.example.therapy_chatbot.response.Messages
import com.example.therapy_chatbot.response.RunResponse
import com.example.therapy_chatbot.response.ThreadAndRunRequest
import com.example.therapy_chatbot.response.ThreadMessagesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface ChatGptService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-prompt-api-key-nclcckrl2rz7u0Lmf6UVT3BlbkFJuBqayHayZTattIxAGO3C",
        "OpenAI-Beta: assistants=v2"
    )
    @POST("threads/{threadID}/messages")
    open fun addMessageToThread(@Body message: Messages?, @Path("threadID") threadID: String): Call<MessageResponse>

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-prompt-api-key-nclcckrl2rz7u0Lmf6UVT3BlbkFJuBqayHayZTattIxAGO3C",
        "OpenAI-Beta: assistants=v2"
    )
    @POST("threads/{threadID}/runs")
    fun runAssistant(
        @Path("threadID") threadID: String,
        @Body request: AssistantRunRequest
    ): Call<RunResponse>

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-prompt-api-key-nclcckrl2rz7u0Lmf6UVT3BlbkFJuBqayHayZTattIxAGO3C",
        "OpenAI-Beta: assistants=v2"
    )
    @POST("threads/runs")
    open fun createRun(@Body request: ThreadAndRunRequest?): Call<RunResponse>

    @Headers(
        "Authorization: Bearer sk-prompt-api-key-nclcckrl2rz7u0Lmf6UVT3BlbkFJuBqayHayZTattIxAGO3C",
        "OpenAI-Beta: assistants=v2"
    )
    @GET("threads/{threadID}/runs/{runID}")
    fun getRunStatus(
        @Path("threadID") threadID: String,
        @Path("runID") runID: String
    ): Call<RunResponse>

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-prompt-api-key-nclcckrl2rz7u0Lmf6UVT3BlbkFJuBqayHayZTattIxAGO3C",
        "OpenAI-Beta: assistants=v2"
    )
    @GET("threads/{threadID}/messages")
    fun getThreadMessages(
        @Path("threadID") threadID: String
    ): Call<ThreadMessagesResponse>


}
