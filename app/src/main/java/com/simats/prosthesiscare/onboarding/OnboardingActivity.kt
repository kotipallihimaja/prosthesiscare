package com.simats.prosthesiscare.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.simats.prosthesiscare.MainActivity
import com.simats.prosthesiscare.data.PreferenceManager
import com.simats.prosthesiscare.ui.theme.ProsthesiscareTheme
import kotlinx.coroutines.delay

class OnboardingActivity : ComponentActivity() {
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager = PreferenceManager(this)
        
        enableEdgeToEdge()
        setContent {
            ProsthesiscareTheme {
                var currentScreen by remember { mutableStateOf("splash") }
                
                // Registration State
                var name by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var age by remember { mutableStateOf("") }
                var isHighRiskUser by remember { mutableStateOf(false) }
                var prosthesisCategory by remember { mutableStateOf("") }
                var prosthesisType by remember { mutableStateOf("") }

                LaunchedEffect(Unit) {
                    delay(2000) // Show splash for 2 seconds
                    if (preferenceManager.isRegistered()) {
                        val userData = preferenceManager.getUserData()
                        navigateToMain(
                            userData.name,
                            userData.email,
                            userData.phone,
                            userData.age,
                            userData.isHighRisk,
                            userData.prosthesisCategory,
                            userData.prosthesisType
                        )
                    } else {
                        currentScreen = "onboarding"
                    }
                }

                when (currentScreen) {
                    "splash" -> SplashScreen()
                    "onboarding" -> OnboardingScreen(onFinished = { currentScreen = "welcome" })
                    "welcome" -> WelcomeScreen(onPatientClick = { currentScreen = "login" })
                    "login" -> LoginScreen(
                        onLoginSuccess = { userName -> 
                            // For demo purposes, we'll just check if registered
                            if (preferenceManager.isRegistered()) {
                                val userData = preferenceManager.getUserData()
                                navigateToMain(
                                    userData.name,
                                    userData.email,
                                    userData.phone,
                                    userData.age,
                                    userData.isHighRisk,
                                    userData.prosthesisCategory,
                                    userData.prosthesisType
                                )
                            } else {
                                // If not registered but "logging in", we use provided name and defaults
                                name = userName
                                navigateToMain(name, "john.doe@email.com", "+1 (555) 123-4567", "65", false, "Fixed", "Crown")
                            }
                        },
                        onRegister = { currentScreen = "register" }
                    )
                    "register" -> RegistrationScreen(
                        onBack = { currentScreen = "login" },
                        onRegister = { n, e, p, a ->
                            name = n
                            email = e
                            phone = p
                            age = a
                            currentScreen = "health_screening"
                        },
                        onLoginClick = { currentScreen = "login" }
                    )
                    "health_screening" -> HealthScreeningScreen(
                        onBack = { currentScreen = "register" },
                        onContinue = { isHighRisk ->
                            isHighRiskUser = isHighRisk
                            currentScreen = "prosthesis_selection"
                        }
                    )
                    "prosthesis_selection" -> ProsthesisSelectionScreen(
                        onBack = { currentScreen = "health_screening" },
                        onContinue = { category, type ->
                            prosthesisCategory = category
                            prosthesisType = type
                            
                            // SAVE DATA HERE
                            preferenceManager.saveUserData(
                                name, email, phone, age, isHighRiskUser, prosthesisCategory, prosthesisType
                            )

                            navigateToMain(name, email, phone, age, isHighRiskUser, prosthesisCategory, prosthesisType)
                        }
                    )
                }
            }
        }
    }

    private fun navigateToMain(
        name: String, 
        email: String, 
        phone: String, 
        age: String, 
        isHighRisk: Boolean,
        category: String,
        type: String
    ) {
        val intent = Intent(this@OnboardingActivity, MainActivity::class.java).apply {
            putExtra("USER_NAME", name)
            putExtra("USER_EMAIL", email)
            putExtra("USER_PHONE", phone)
            putExtra("USER_AGE", age)
            putExtra("IS_HIGH_RISK", isHighRisk)
            putExtra("PROSTHESIS_CATEGORY", category)
            putExtra("PROSTHESIS_TYPE", type)
        }
        startActivity(intent)
        finish()
    }
}
