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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String,
    email: String,
    phone: String,
    age: String,
    prosthesisCategory: String,
    prosthesisType: String,
    isHighRisk: Boolean,
    onBack: () -> Unit,
    onEditClick: () -> Unit,
    onProsthesisDetailsClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Menu action */ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "Menu", tint = Color.White)
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
            // Profile Header Card with JD circle and user info
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(OnboardingTeal, Color(0xFF00897B))
                            )
                        )
                        .padding(24.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (userName.isNotEmpty()) {
                                        val parts = userName.trim().split(" ")
                                        if (parts.size >= 2) {
                                            (parts[0].take(1) + parts[1].take(1)).uppercase()
                                        } else {
                                            parts[0].take(2).uppercase()
                                        }
                                    } else "JD",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnboardingTeal
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Text(text = userName, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Patient ID: #12345", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        Divider(color = Color.White.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(text = "Member Since", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                Text(text = "Aug 2025", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = "Compliance", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                Text(text = "85%", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "Account Settings", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
            
            Spacer(modifier = Modifier.height(16.dp))

            // Settings List
            SettingsItem(R.drawable.ic_person, "Edit Profile", onClick = onEditClick)
            SettingsItem(R.drawable.ic_shield_check, "Prosthesis Details", onClick = onProsthesisDetailsClick)
            SettingsItem(R.drawable.ic_call, "Emergency Contact", iconTint = Color.Red, onClick = {})
            SettingsItem(R.drawable.ic_settings, "Language & Accessibility", onClick = onLanguageClick)
            SettingsItem(R.drawable.ic_lock, "Privacy & Consent", onClick = onPrivacyClick)
            SettingsItem(R.drawable.ic_message, "Feedback & Support", onClick = {})
            SettingsItem(R.drawable.ic_info, "App Info", onClick = {})

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = null, tint = Color.Red, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsItem(icon: Int, title: String, iconTint: Color = OnboardingTeal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = if(iconTint == OnboardingTeal) 0.1f else 0.05f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, modifier = Modifier.weight(1f), fontSize = 16.sp, color = Color(0xFF333333))
            Icon(painter = painterResource(id = R.drawable.ic_chevron_right), contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
        }
    }
}
