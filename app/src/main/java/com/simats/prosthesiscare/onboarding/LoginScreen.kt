package com.simats.prosthesiscare.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.R
import com.simats.prosthesiscare.ui.theme.DarkGrey
import com.simats.prosthesiscare.ui.theme.LightGrey
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Welcome Back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGrey
        )

        Text(
            text = "Sign in to continue",
            fontSize = 16.sp,
            color = LightGrey,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(text = "Email or Phone", color = DarkGrey, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            placeholder = { Text("Enter your email or phone", color = LightGrey) },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = null, modifier = Modifier.size(20.dp))
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OnboardingTeal,
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Password", color = DarkGrey, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            placeholder = { Text("Enter your password", color = LightGrey) },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_lock), contentDescription = null, modifier = Modifier.size(20.dp))
            },
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_visibility), contentDescription = null, modifier = Modifier.size(20.dp))
            },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OnboardingTeal,
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        TextButton(
            onClick = { /* Forgot password logic */ },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text = "Forgot Password?", color = OnboardingTeal)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onLoginSuccess(email) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
        ) {
            Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account? ", color = LightGrey)
            Text(
                text = "Register", 
                color = OnboardingTeal, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onRegister() }
            )
        }
    }
}
