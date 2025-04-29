package com.example.foodike.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

@Composable
fun GreetingSection() {
    val c: Calendar = Calendar.getInstance()
    val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)

    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: "User"
    val username = userEmail.substringBefore("@").replaceFirstChar {
        it.uppercaseChar() // ✅ Safe and clean
    }

    val greeting = when (timeOfDay) {
        in 0..11 -> "Good Morning"
        in 12..15 -> "Good Afternoon"
        else -> "Good Evening"
    }

    Column(modifier = Modifier.padding(16.dp, 0.dp)) {
        Text(
            text = "$greeting $username!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light
        )
    }
}
