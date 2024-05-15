package com.example.therapy_chatbot.ui.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapy_chatbot.MainViewModel
import com.example.therapy_chatbot.data.Message
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WindowContent(messages: List<Message>, viewModel: MainViewModel) {
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size - 1)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            reverseLayout = false,
        ) {
            items(messages.size) { index ->
                val message = messages[index]
                MessageCard(message = message)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        MessageInput(viewModel)
    }
}

@Composable
fun MessageCard(message: Message) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.senderIsMe) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (message.senderIsMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp, 9.dp)
        ) {
            Text(
                text = message.content,
                color = if (message.senderIsMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            )
        }
        Text(
            text = message.time,
            color = if (message.senderIsMe) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiary,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(viewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = { text = it },
            label = { Text("Message") },
            maxLines = 4,
            trailingIcon = {
                Box(modifier = Modifier.clickable {
                    val tempText = text
                    viewModel.messages.value += Message(
                        true,
                        tempText,
                        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                    )
                    text = ""
                    isLoading = true
                    focusManager.clearFocus() // Clear focus to hide the keyboard
                    coroutineScope.launch {
                        val answer = viewModel.sendMessageToAssistant(tempText)
                        isLoading = false
                        viewModel.messages.value += Message(
                            false,
                            answer,
                            time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                        )
                    }
                }) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    } else {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )
    }
}


