package com.example.therapy_chatbot

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

import com.example.therapy_chatbot.network.NetworkRepository
import com.example.therapy_chatbot.ui.common.TopBar
import com.example.therapy_chatbot.ui.common.WindowContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel()
    private var isFirstTime = true

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Therapy_chatbotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isFirstTime) {
                        // Create a run with the desired assistant ID and send a greeting message
                        GlobalScope.launch(Dispatchers.IO) {
                            NetworkRepository.createRunAndSendMessage()
                        }
                        isFirstTime = false
                    }
                    ChatScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ChatScreen(viewModel: MainViewModel) {
    var message by remember { mutableStateOf(TextFieldValue()) }
    var response by remember { mutableStateOf("") }
    val context = LocalContext.current
    val messages= viewModel.messages
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar()
       WindowContent(messages = messages.value, viewModel = viewModel)




    }
}