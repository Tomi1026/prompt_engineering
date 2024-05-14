package com.example.therapy_chatbot.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp),
    ){
        Row(
          modifier = Modifier
              .fillMaxSize()
              .padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.size(16.dp))
            Icon(imageVector = Icons.Default.Face , contentDescription = "Icon" )
            Spacer(modifier = Modifier.size(16.dp))
            Column(){
                Text("Pro Therapist", style = MaterialTheme.typography.titleLarge)
                Text("Online", style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = Color(0xFF06a94d))
            }

        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar()
}