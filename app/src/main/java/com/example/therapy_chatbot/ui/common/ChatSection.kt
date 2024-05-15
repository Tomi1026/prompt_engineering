package com.example.therapy_chatbot.ui.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.therapy_chatbot.MainViewModel
import com.example.therapy_chatbot.data.Message
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WindowContent(messages: List<Message>, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .weight(7f),
            reverseLayout = false,
        ){
            items(messages.size){
                val message = messages[it]
                MessageCard(
                    message = message
                )
                Spacer(modifier = Modifier.padding(8.dp))

            }
        }
        MessageInput(viewModel, Modifier.weight(1f))
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

        )
        {
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
fun MessageInput(viewModel: MainViewModel, modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    )

    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("Message") },
            maxLines = 4,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    modifier = Modifier.clickable {
                        viewModel.messages.value += Message(true, text, time = LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("HH:mm")))

                        // viewModel.sendMessageToAssistant(text)
                        text = ""
                    }
                )
            }
        )
    }
}





