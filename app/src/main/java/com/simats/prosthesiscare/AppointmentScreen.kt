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
fun AppointmentScreen(onBack: () -> Unit, onViewHistory: () -> Unit, onViewDetails: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Appointments", color = Color.White, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = OnboardingTeal)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add appointment */ },
                containerColor = OnboardingTeal,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
            }
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
            Text(text = "Upcoming Visits", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkGrey)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = "Filter by Month", color = DarkGrey, fontSize = 14.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFFE0E0E0))
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppointmentItem("Adjustment", "Feb 15, 2026", "2:00 PM", "Dr. Michael Chen", onViewDetails)
            AppointmentItem("Follow-up", "Mar 10, 2026", "11:00 AM", "Dr. Emily Rodriguez", onViewDetails)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = onViewHistory,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkGrey),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
            ) {
                Text("View Past Appointments")
            }
        }
    }
}

@Composable
fun AppointmentItem(title: String, date: String, time: String, doctor: String, onViewDetails: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkGrey)
                Box(modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(OnboardingTeal.copy(alpha = 0.1f)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                    Text(text = "Upcoming", color = OnboardingTeal, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null, tint = LightGrey, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date, color = LightGrey, fontSize = 14.sp)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_access_time), contentDescription = null, tint = LightGrey, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = time, color = LightGrey, fontSize = 14.sp)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = null, tint = LightGrey, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = doctor, color = LightGrey, fontSize = 14.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { /* Reschedule */ },
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F3F4), contentColor = DarkGrey)
                ) {
                    Text("Reschedule", fontSize = 14.sp)
                }
                Button(
                    onClick = onViewDetails,
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
                ) {
                    Text("View Details", fontSize = 14.sp)
                }
            }
        }
    }
}
