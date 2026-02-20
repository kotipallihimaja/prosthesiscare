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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

data class ReminderItem(
    val title: String,
    val date: String,
    val time: String? = null,
    val isCompleted: Boolean
)

val reminderHistoryData = listOf(
    ReminderItem("Daily Cleaning", "Jan 27, 2026", "9:15 AM", true),
    ReminderItem("Daily Cleaning", "Jan 26, 2026", "9:20 AM", true),
    ReminderItem("Weekly Deep Clean", "Jan 26, 2026", "8:00 PM", true),
    ReminderItem("Daily Cleaning", "Jan 25, 2026", null, false),
    ReminderItem("Daily Cleaning", "Jan 24, 2026", "9:10 AM", true),
    ReminderItem("Monthly Inspection", "Jan 20, 2026", "7:30 PM", true),
    ReminderItem("Weekly Deep Clean", "Jan 19, 2026", "8:15 PM", true),
    ReminderItem("Daily Cleaning", "Jan 18, 2026", null, false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderHistoryScreen(onBack: () -> Unit) {
    var selectedFilter by remember { mutableStateOf("All") }
    val scrollState = rememberScrollState()

    val filteredReminders = when (selectedFilter) {
        "Completed" -> reminderHistoryData.filter { it.isCompleted }
        "Missed" -> reminderHistoryData.filter { !it.isCompleted }
        else -> reminderHistoryData
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Maintenance History", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
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
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Overall Compliance Card
            ComplianceCard()

            Spacer(modifier = Modifier.height(24.dp))

            // Filter Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("All", "Completed", "Missed").forEach { tab ->
                    val isSelected = selectedFilter == tab
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) OnboardingTeal else Color.Transparent)
                            .clickable { selectedFilter = tab },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            color = if (isSelected) Color.White else Color.Gray,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // History List
            filteredReminders.forEach { reminder ->
                HistoryItemRow(reminder)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ComplianceCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = OnboardingTeal)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(OnboardingTeal, Color(0xFF00897B))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Text(text = "Overall Compliance", color = Color.White.copy(alpha = 0.9f), fontSize = 16.sp)
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "75%", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Completed: 6", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                        Text(text = "Missed: 2", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                LinearProgressIndicator(
                    progress = { 0.75f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
fun HistoryItemRow(reminder: ReminderItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
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
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (reminder.isCompleted) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (reminder.isCompleted) R.drawable.ic_check_circle else R.drawable.ic_cancel),
                    contentDescription = null,
                    tint = if (reminder.isCompleted) Color(0xFF4CAF50) else Color(0xFFF44336),
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(text = reminder.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (reminder.time != null) "${reminder.date} • ${reminder.time}" else reminder.date,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (reminder.isCompleted) Color(0xFFE8F5E9) else Color(0xFFFFEBEE))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (reminder.isCompleted) "Done" else "Missed",
                    color = if (reminder.isCompleted) Color(0xFF4CAF50) else Color(0xFFF44336),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
