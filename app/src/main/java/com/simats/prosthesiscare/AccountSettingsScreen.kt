package com.simats.prosthesiscare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.DarkGrey
import com.simats.prosthesiscare.ui.theme.LightGrey
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    userName: String,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White, fontSize = 20.sp) },
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
                .background(Color(0xFFF8F9FA))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = OnboardingTeal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (userName.isNotEmpty()) userName.take(2).uppercase() else "JD",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnboardingTeal
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(text = userName, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Patient ID: #12345", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(color = Color.White.copy(alpha = 0.2f))
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Member Since", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                            Text("Aug 2025", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Compliance", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                            Text("85%", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Account Settings", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkGrey)
            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem("Edit Profile", R.drawable.ic_person, Color(0xFFE3F2FD), Color(0xFF2196F3))
            SettingsItem("Prosthesis Details", R.drawable.ic_anchor, Color(0xFFE0F2F1), Color(0xFF009688))
            SettingsItem("Emergency Contact", R.drawable.ic_call, Color(0xFFFFEBEE), Color(0xFFD32F2F))
            SettingsItem("Language & Accessibility", R.drawable.ic_sparkle, Color(0xFFF3E5F5), Color(0xFF9C27B0))
            SettingsItem("Privacy & Consent", R.drawable.ic_shield_check, Color(0xFFE8F5E9), Color(0xFF4CAF50))
            SettingsItem("Feedback & Support", R.drawable.ic_notifications_none, Color(0xFFFFF3E0), Color(0xFFFF9800))
            SettingsItem("App Info", R.drawable.ic_history, Color(0xFFF5F5F5), DarkGrey)

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable { onLogout() },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = null, tint = Color(0xFFD32F2F))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SettingsItem(title: String, icon: Int, bgColor: Color, iconColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { /* Action */ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, modifier = Modifier.weight(1f), color = DarkGrey, fontWeight = FontWeight.Medium)
            Icon(painter = painterResource(id = R.drawable.ic_chevron_right), contentDescription = null, tint = LightGrey, modifier = Modifier.size(20.dp))
        }
    }
}
