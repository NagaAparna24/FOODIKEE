
package com.example.foodike.presentation.signup

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodike.R
import com.example.foodike.presentation.login.components.FoodikeTextField
import com.example.foodike.presentation.login.FoodikeTextFieldState
import com.example.foodike.presentation.util.Screen

@Composable
fun SignupScreen(
    emailState: FoodikeTextFieldState,
    passwordState: FoodikeTextFieldState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignupClick: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current as Activity
    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account", fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(horizontalAlignment = Alignment.End) {
            FoodikeTextField(
                value = emailState.text,
                onValueChange = onEmailChanged,
                hint = emailState.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            FoodikeTextField(
                value = passwordState.text,
                onValueChange = onPasswordChanged,
                hint = passwordState.hint
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.width(200.dp), onClick = onSignupClick) {
            Text(text = "Sign Up", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Already a user? Login here",
            modifier = Modifier
                .alpha(0.5f)
                .clickable {
                    navController.navigate(Screen.LoginScreen.route)
                }
        )
    }
}
