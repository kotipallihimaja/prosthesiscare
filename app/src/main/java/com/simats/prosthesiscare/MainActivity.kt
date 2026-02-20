package com.simats.prosthesiscare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.simats.prosthesiscare.ui.theme.ProsthesiscareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val userName = intent.getStringExtra("USER_NAME") ?: "Patient"
        val email = intent.getStringExtra("USER_EMAIL") ?: "not.provided@email.com"
        val phone = intent.getStringExtra("USER_PHONE") ?: "Not provided"
        val age = intent.getStringExtra("USER_AGE") ?: "0"
        val isHighRisk = intent.getBooleanExtra("IS_HIGH_RISK", false)
        val category = intent.getStringExtra("PROSTHESIS_CATEGORY") ?: "Unknown"
        val type = intent.getStringExtra("PROSTHESIS_TYPE") ?: "Unknown"
        
        setContent {
            ProsthesiscareTheme {
                HomeScreen(
                    userName = userName,
                    email = email,
                    phone = phone,
                    age = age,
                    isHighRisk = isHighRisk,
                    prosthesisCategory = category,
                    prosthesisType = type,
                    onLogout = { finish() }
                )
            }
        }
    }
}
