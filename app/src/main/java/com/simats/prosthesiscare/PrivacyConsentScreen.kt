package com.simats.prosthesiscare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyConsentScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var healthDataCollection by remember { mutableStateOf(true) }
    var reminderNotifications by remember { mutableStateOf(true) }
    var anonymousAnalytics by remember { mutableStateOf(false) }
    var marketingCommunications by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Privacy & Consent", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            // Privacy Header Banner
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF00C853)) // Green banner
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Your Privacy Matters",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "We protect your health information with industry-standard encryption",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Text(
                text = "Data Permissions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Permission Items
            PrivacyToggleItem(
                title = "Health Data Collection",
                subtitle = "Allow app to store your prosthesis maintenance records for app functionality",
                icon = R.drawable.ic_assignment,
                iconTint = Color(0xFF009688),
                checked = healthDataCollection,
                onCheckedChange = { healthDataCollection = it }
            )

            PrivacyToggleItem(
                title = "Reminder Notifications",
                subtitle = "Send maintenance and appointment reminders",
                icon = R.drawable.ic_visibility,
                iconTint = Color(0xFF2196F3),
                checked = reminderNotifications,
                onCheckedChange = { reminderNotifications = it }
            )

            PrivacyToggleItem(
                title = "Anonymous Analytics",
                subtitle = "Help improve the app with anonymous usage data",
                icon = R.drawable.ic_lock,
                iconTint = Color(0xFF9C27B0),
                checked = anonymousAnalytics,
                onCheckedChange = { anonymousAnalytics = it }
            )

            PrivacyToggleItem(
                title = "Marketing Communications",
                subtitle = "Receive health tips and product updates",
                icon = R.drawable.ic_shield_check, // Using shield as placeholder
                iconTint = Color(0xFFFF7043),
                checked = marketingCommunications,
                onCheckedChange = { marketingCommunications = it }
            )

            // Compliance Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "HIPAA Compliance",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "This app complies with HIPAA privacy rules to protect your health information.",
                        fontSize = 14.sp,
                        color = Color(0xFF1976D2)
                    )
                    Text(
                        text = "Read Privacy Policy",
                        fontSize = 14.sp,
                        color = Color(0xFF1976D2),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { /* Link to policy */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
            ) {
                Text("Save Privacy Settings", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun PrivacyToggleItem(
    title: String,
    subtitle: String,
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
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp).padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF37474F)
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
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
