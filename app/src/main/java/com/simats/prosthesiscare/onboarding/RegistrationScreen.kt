package com.simats.prosthesiscare.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.R
import com.simats.prosthesiscare.ui.theme.DarkGrey
import com.simats.prosthesiscare.ui.theme.LightGrey
import com.simats.prosthesiscare.ui.theme.OnboardingTeal
import java.util.*
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onBack: () -> Unit, 
    onRegister: (String, String, String, String) -> Unit, 
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        dob = sdf.format(Date(it))
                    }
                    showDatePicker = false
                }) {
                    Text("OK", color = OnboardingTeal)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = OnboardingTeal)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Patient Registration", color = Color.White, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = OnboardingTeal)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(text = "Please fill in your details to create\nan account", color = LightGrey, fontSize = 16.sp)
            
            Spacer(modifier = Modifier.height(24.dp))

            RegistrationField("Full Name *", fullName, { fullName = it }, R.drawable.ic_person, "Enter your full name")
            RegistrationField("Email *", email, { email = it }, R.drawable.ic_email, "Enter your email")
            RegistrationField("Phone Number *", phone, { phone = it }, R.drawable.ic_phone, "Enter your phone number")
            RegistrationField("Age *", age, { age = it }, null, "Enter your age")
            
            // DOB field with manual entry and calendar icon
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(text = "Date of Birth", color = DarkGrey, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                OutlinedTextField(
                    value = dob,
                    onValueChange = { dob = it },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    placeholder = { Text("DD/MM/YYYY", color = LightGrey, fontSize = 14.sp) },
                    leadingIcon = { 
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar), 
                            contentDescription = null, 
                            modifier = Modifier.size(20.dp).clickable { showDatePicker = true },
                            tint = LightGrey
                        ) 
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OnboardingTeal, unfocusedBorderColor = Color(0xFFE0E0E0))
                )
            }

            RegistrationField("Password *", password, { password = it }, R.drawable.ic_lock, "Create a password", isPassword = true)
            RegistrationField("Confirm Password *", confirmPassword, { confirmPassword = it }, R.drawable.ic_lock, "Confirm your password", isPassword = true)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onRegister(fullName, email, phone, age) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text("Already have an account? ", color = LightGrey, fontSize = 14.sp)
                Text(
                    text = "Login", 
                    color = OnboardingTeal, 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun RegistrationField(label: String, value: String, onValueChange: (String) -> Unit, icon: Int?, placeholder: String, isPassword: Boolean = false) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(text = label, color = DarkGrey, fontWeight = FontWeight.Medium, fontSize = 14.sp)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            placeholder = { Text(placeholder, color = LightGrey, fontSize = 14.sp) },
            leadingIcon = icon?.let { { Icon(painter = painterResource(id = it), contentDescription = null, modifier = Modifier.size(20.dp), tint = LightGrey) } },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OnboardingTeal, unfocusedBorderColor = Color(0xFFE0E0E0))
        )
    }
}
