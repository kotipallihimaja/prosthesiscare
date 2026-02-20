package com.simats.prosthesiscare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderSettingsScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var dailyCleaning by remember { mutableStateOf(true) }
    var weeklyDeepClean by remember { mutableStateOf(true) }
    var monthlyInspection by remember { mutableStateOf(true) }
    var appointments by remember { mutableStateOf(true) }
    var sound by remember { mutableStateOf(true) }
    var vibration by remember { mutableStateOf(true) }
    
    var dailyTime by remember { mutableStateOf("08:00 AM") }
    var weeklyDay by remember { mutableStateOf("Sunday") }
    var appointmentReminder by remember { mutableStateOf("24 Hours Before") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Reminder Settings", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
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
                .background(Color(0xFFF8F9FA))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Reminder Types",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ReminderTypeItem(
                title = "Daily Cleaning",
                subtitle = "Morning & evening reminders",
                icon = R.drawable.ic_notifications_none,
                iconTint = OnboardingTeal,
                checked = dailyCleaning,
                onCheckedChange = { dailyCleaning = it }
            )

            ReminderTypeItem(
                title = "Weekly Deep Clean",
                subtitle = "Once per week",
                icon = R.drawable.ic_notifications_none,
                iconTint = Color.Blue,
                checked = weeklyDeepClean,
                onCheckedChange = { weeklyDeepClean = it }
            )

            ReminderTypeItem(
                title = "Monthly Inspection",
                subtitle = "Once per month",
                icon = R.drawable.ic_notifications_none,
                iconTint = Color(0xFF9C27B0),
                checked = monthlyInspection,
                onCheckedChange = { monthlyInspection = it }
            )

            ReminderTypeItem(
                title = "Appointments",
                subtitle = "Follow-up reminders",
                icon = R.drawable.ic_notifications_none,
                iconTint = Color(0xFFFF7043),
                checked = appointments,
                onCheckedChange = { appointments = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Timing",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TimingField(label = "Daily Reminder Time", value = dailyTime, icon = R.drawable.ic_access_time)
            TimingField(label = "Weekly Reminder Day", value = weeklyDay, icon = null)
            TimingField(label = "Appointment Reminder", value = appointmentReminder, icon = null)

            Spacer(modifier = Modifier.height(24.dp))

            ReminderTypeItem(
                title = "Sound",
                subtitle = null,
                icon = R.drawable.ic_notifications_none, // Should be volume icon
                iconTint = OnboardingTeal,
                checked = sound,
                onCheckedChange = { sound = it }
            )

            ReminderTypeItem(
                title = "Vibration",
                subtitle = null,
                icon = R.drawable.ic_notifications_none, // Should be vibration icon
                iconTint = OnboardingTeal,
                checked = vibration,
                onCheckedChange = { vibration = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
            ) {
                Text("Save Preferences", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ReminderTypeItem(
    title: String,
    subtitle: String?,
    icon: Int,
    iconTint: Color,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF37474F)
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = OnboardingTeal,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFE0E0E0),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun TimingField(label: String, value: String, icon: Int?) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = label, fontSize = 14.sp, color = Color(0xFF37474F))
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color(0xFFEEEEEE)
            )
        )
    }
}
