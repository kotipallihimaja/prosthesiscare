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
fun AppointmentHistoryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Appointment History", color = Color.White, fontSize = 20.sp) },
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
            // Visit Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Visit Summary", fontSize = 16.sp, color = DarkGrey)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        SummaryBox("4", "Completed", Color(0xFFE8F5E9), Color(0xFF4CAF50), Modifier.weight(1f))
                        SummaryBox("1", "Missed", Color(0xFFFFEBEE), Color(0xFFF44336), Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Past Appointments", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkGrey)
            
            Spacer(modifier = Modifier.height(16.dp))

            PastAppointmentItem("6-Month Check-up", "Jan 15, 2026", "Dr. Sarah Johnson", true)
            PastAppointmentItem("Adjustment", "Dec 20, 2025", "Dr. Michael Chen", true)
            PastAppointmentItem("Follow-up", "Nov 10, 2025", "Dr. Emily Rodriguez", true)
            PastAppointmentItem("Initial Fitting", "Oct 5, 2025", "Dr. Sarah Johnson", true)
            PastAppointmentItem("Routine Check", "Sep 15, 2025", "Dr. Michael Chen", false)
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SummaryBox(count: String, label: String, bgColor: Color, textColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textColor)
            Text(text = label, fontSize = 12.sp, color = DarkGrey)
        }
    }
}

@Composable
fun PastAppointmentItem(title: String, date: String, doctor: String, isDone: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
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
                    .background(if (isDone) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (isDone) R.drawable.ic_check_circle else R.drawable.ic_cancel),
                    contentDescription = null,
                    tint = if (isDone) Color(0xFF4CAF50) else Color(0xFFF44336),
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkGrey)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isDone) Color(0xFFE8F5E9) else Color(0xFFFFEBEE))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (isDone) "Done" else "Missed",
                            color = if (isDone) Color(0xFF4CAF50) else Color(0xFFF44336),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null, tint = LightGrey, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = date, color = LightGrey, fontSize = 13.sp)
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = null, tint = LightGrey, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = doctor, color = LightGrey, fontSize = 13.sp)
                }
            }
        }
    }
}
