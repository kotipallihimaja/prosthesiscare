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
fun AppointmentDetailsScreen(onBack: () -> Unit, onConfirm: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Appointment Details", color = Color.White, fontSize = 20.sp) },
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
            // Header Card (Teal Card)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = OnboardingTeal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Upcoming Appointment", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                            Text("6-Month Check-up", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("February 5, 2026", color = Color.White, fontSize = 16.sp)
                            Text("10:30 AM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Information Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Appointment Information", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkGrey)
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    InfoRow(R.drawable.ic_person, "Dentist", "Dr. Sarah Johnson\nProsthodontist")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(R.drawable.ic_location_on, "Location", "Smile Dental Clinic\n123 Health Street, Medical District")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(R.drawable.ic_call, "Contact", "+1 (555) 123-4567")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(R.drawable.ic_access_time, "Duration", "30-45 minutes")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reminder Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_notifications_none), contentDescription = null, tint = Color(0xFF2196F3), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Reminder Set", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("You'll receive a notification 1 day before\nand 1 hour before your appointment", color = Color(0xFF2196F3), fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Checklist Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_assignment), contentDescription = null, tint = Color(0xFF8D6E63), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Before Your Visit", color = Color(0xFF5D4037), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ChecklistItem("Bring your prosthesis maintenance log")
                    ChecklistItem("Note any discomfort or issues")
                    ChecklistItem("Clean your prosthesis before visit")
                    ChecklistItem("Arrive 10 minutes early")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bottom Buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
                ) {
                    Text("Reschedule", color = DarkGrey)
                }
                Button(
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
                ) {
                    Text("Confirm", fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InfoRow(icon: Int, label: String, value: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(painter = painterResource(id = icon), contentDescription = null, tint = OnboardingTeal, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(label, color = LightGrey, fontSize = 12.sp)
            Text(value, color = DarkGrey, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ChecklistItem(text: String) {
    Text(text = "• $text", color = Color(0xFF795548), fontSize = 13.sp, modifier = Modifier.padding(vertical = 2.dp))
}
