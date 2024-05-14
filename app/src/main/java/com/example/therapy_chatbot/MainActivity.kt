package com.example.therapy_chatbot

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapy_chatbot.ui.theme.Therapy_chatbotTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.example.therapy_chatbot.network.ChatGptService
import com.example.therapy_chatbot.network.NetworkRepository
import com.example.therapy_chatbot.response.Messages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private var isFirstTime = true

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Therapy_chatbotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    if (isFirstTime) {
                        // Create a run with the desired assistant ID and send a greeting message
                        GlobalScope.launch(Dispatchers.IO) {
                            NetworkRepository.createRunAndSendMessage()
                        }
                        isFirstTime = false
                    }
                    ChatScreen()
                }
            }
        }
    }
}

@Composable
fun ChatScreen() {
    var message by remember { mutableStateOf(TextFieldValue()) }
    var response by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            BasicTextField(
                value = message,
                onValueChange = {
                    message = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(textAlign = TextAlign.Start),
                singleLine = true
            )
            Button(
                onClick = {
                    sendMessageToAssistant(message.text)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Send")
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = response,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun sendMessageToAssistant(message: String) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ChatGptService::class.java)
    val request = Messages(role= "user", content = message)

    GlobalScope.launch(Dispatchers.IO) {
        try {
            NetworkRepository.addMessageAndRunAssistant(message)
        } catch (e: Exception) {
            // Handle exception
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Therapy_chatbotTheme {
        ChatScreen()
    }
}