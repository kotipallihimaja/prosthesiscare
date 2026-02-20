package com.simats.prosthesiscare

import androidx.compose.foundation.background
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
fun ProsthesisOverviewScreen(
    category: String,
    type: String,
    onBack: () -> Unit,
    onUpdateClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prosthesis Overview", color = Color.White, fontSize = 20.sp) },
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
            // Overview Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0F2F1)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = if(category == "Fixed") R.drawable.ic_anchor else R.drawable.ic_sentiment_satisfied),
                                contentDescription = null,
                                tint = OnboardingTeal,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(text = type, color = DarkGrey, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(text = "$category Prosthesis", color = LightGrey, fontSize = 14.sp)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    OverviewInfoRow("Category", category)
                    OverviewInfoRow("Type", type)
                    OverviewInfoRow("Installation Date", "Aug 15, 2025")
                    OverviewInfoRow("Last Maintenance", "Jan 25, 2026")
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Care Status", color = LightGrey, fontSize = 14.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painter = painterResource(id = R.drawable.ic_check_circle), contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Good", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Compliance Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = OnboardingTeal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Maintenance Compliance", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LinearProgressIndicator(
                            progress = { 0.85f },
                            modifier = Modifier.weight(1f).height(10.dp).clip(CircleShape),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.3f)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("85%", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Great job! You've completed 17 out of 20\nmaintenance tasks this month",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Recent Activity", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkGrey)
            Spacer(modifier = Modifier.height(16.dp))

            ActivityItem("Daily Cleaning Completed", "Today at 9:15 AM", R.drawable.ic_check_circle, Color(0xFFE8F5E9), Color(0xFF4CAF50))
            ActivityItem("Follow-up Scheduled", "Yesterday at 2:30 PM", R.drawable.ic_calendar, Color(0xFFE3F2FD), Color(0xFF2196F3))
            ActivityItem("Weekly Cleaning Missed", "2 days ago", R.drawable.ic_warning, Color(0xFFFFEBEE), Color(0xFFFF9800))

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onUpdateClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
            ) {
                Text("Update Prosthesis Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun OverviewInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = LightGrey, fontSize = 14.sp)
        Text(value, color = DarkGrey, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
    Divider(color = Color(0xFFF1F1F1))
}

@Composable
fun ActivityItem(title: String, time: String, icon: Int, bgColor: Color, iconColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, color = DarkGrey, fontSize = 14.sp)
                Text(text = time, color = LightGrey, fontSize = 12.sp)
            }
        }
    }
}
