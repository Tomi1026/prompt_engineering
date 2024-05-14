package com.example.therapy_chatbot.network
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.therapy_chatbot.response.AssistantRunRequest
import com.example.therapy_chatbot.response.Messages
import com.example.therapy_chatbot.response.RunResponse
import com.example.therapy_chatbot.response.Thread
import com.example.therapy_chatbot.response.ThreadAndRunRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object NetworkRepository {
    private val apiClient = ApiClient.getInstance()
    val threadID = mutableStateOf("")
    val runID = mutableStateOf("")
    val assistantID = "asst_I1m0AT4NQjqMwwyKp80SJqOT"

    suspend fun createRunAndSendMessage() {
        try {
            // Step 1: Create a run with the desired assistant ID
            val greetingMessage = Messages(role = "assistant", content = "Hello! How can I assist you today?")
            val createRunRequest = ThreadAndRunRequest(assistantId =assistantID, thread = Thread(arrayListOf(greetingMessage)))
            val runResponse = apiClient.createRun(createRunRequest).execute()

            if (runResponse.isSuccessful) {
                val runResponseBody = runResponse.body()
                threadID.value = runResponseBody?.threadId ?: ""
                runID.value = runResponseBody?.id ?: ""
                Log.d("runResponseBody",threadID.value)
                Log.d("runResponseBody", runID.value)
            } else {
                println("Failed to create run: ${runResponse.errorBody()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun addMessageAndRunAssistant(messagestring: String) {
        try {
            val message = Messages(role = "user", content = messagestring)
            val addMessage =  apiClient.addMessageToThread(message, threadID.value)
            // Step 3: Add a new message to the thread
            var addMessageResponse = addMessage.execute()
            Log.d("addMessageResponse",addMessageResponse.toString())
            Log.d("addMessageResponse",addMessage.toString())
            Log.d("addMessageResponse",message.toString())

            if (addMessageResponse.isSuccessful) {
                // Step 4: Run the assistant
                var runResponse = apiClient.runAssistant(threadID.value, AssistantRunRequest(assistantId = assistantID)).execute()

                if (runResponse.isSuccessful) {
                    var runResponseBody = runResponse.body()
                    runID.value = runResponseBody?.id ?: ""
                    Log.d("runID" , runID.value)
                    while (runResponseBody?.status != "completed"){
                        java.lang.Thread.sleep(5000)
                        Log.d("Waiting for assistant response",runResponseBody?.status.toString())
                        runResponse = apiClient.getRunStatus(threadID.value, runID.value).execute()
                        if(runResponse.isSuccessful)
                            runResponseBody = runResponse.body()
                    }
                    Log.d("Finished waiting",runResponseBody.toString())
                    if (runResponseBody?.status == "completed") {
                        val threadMessagesResponse = apiClient.getThreadMessages(threadID = threadID.value).execute()
                        if(threadMessagesResponse.isSuccessful){
                            val threadMessagesResponseBody = threadMessagesResponse.body()
                            Log.d("threadMessagesResponseBody",threadMessagesResponseBody.toString())
                            println("Assistant response: ${threadMessagesResponseBody?.data}")
                            println("Assistant response: ${threadMessagesResponseBody?.data?.lastOrNull()}")

                        }
                        else{
                            println("Failed to get messages: ${threadMessagesResponse.errorBody()}")
                        }
                        val threadMessagesResponseBody = threadMessagesResponse.body()

                    } else {
                        println("Assistant run is not completed yet")
                    }
                } else {
                    println("Failed to run assistant: ${runResponse.errorBody()}")
                }
            } else {
                println("Failed to add message: ${addMessageResponse.errorBody()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
