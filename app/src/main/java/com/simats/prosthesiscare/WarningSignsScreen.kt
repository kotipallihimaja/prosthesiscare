package com.simats.prosthesiscare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
fun WarningSignsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Warning Signs", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
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
            // Header Warning Card with Gradient
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFF5722), Color(0xFFFF7043))
                            )
                        )
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_warning), contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Warning Signs", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text("Contact your dentist if you notice any of these issues", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            WarningItemRow("Persistent Pain or Discomfort", "Pain that doesn't subside after a few days or increases over time", "Urgent", Color(0xFFFFEBEE), Color(0xFFD32F2F))
            WarningItemRow("Loose or Ill-Fitting Prosthesis", "Denture moves excessively or feels uncomfortable when eating or speaking", "Monitor", Color(0xFFFFF9C4), Color(0xFFFBC02D))
            WarningItemRow("Cracks or Chips", "Visible damage to the prosthesis structure", "Urgent", Color(0xFFFFEBEE), Color(0xFFD32F2F))
            WarningItemRow("Bad Odor or Taste", "Persistent bad smell even after thorough cleaning", "Monitor", Color(0xFFFFF9C4), Color(0xFFFBC02D))
            WarningItemRow("Sore Spots or Ulcers", "Red, swollen, or bleeding areas in the mouth or gums", "Urgent", Color(0xFFFFEBEE), Color(0xFFD32F2F))
            WarningItemRow("Difficulty Chewing", "Increased difficulty eating foods you could eat before", "Monitor", Color(0xFFFFF9C4), Color(0xFFFBC02D))
            WarningItemRow("Speech Problems", "New difficulty pronouncing words or speaking clearly", "Monitor", Color(0xFFFFF9C4), Color(0xFFFBC02D))
            WarningItemRow("Discoloration or Staining", "Persistent stains that don't come off with regular cleaning", "Low", Color(0xFFE3F2FD), Color(0xFF1976D2))

            Spacer(modifier = Modifier.height(24.dp))

            // Seek Immediate Care Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                shape = RoundedCornerShape(12.dp),
                border = Box(modifier = Modifier.padding(1.dp).background(Color(0xFFFFCDD2), RoundedCornerShape(12.dp))).let { null } // Placeholder for border logic if needed
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_warning), contentDescription = null, tint = Color(0xFFD32F2F), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("When to Seek Immediate Care", color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("• Severe pain that doesn't respond to pain medication", color = Color(0xFFD32F2F), fontSize = 13.sp, modifier = Modifier.padding(bottom = 4.dp))
                    Text("• Bleeding that won't stop", color = Color(0xFFD32F2F), fontSize = 13.sp, modifier = Modifier.padding(bottom = 4.dp))
                    Text("• Signs of infection (fever, pus, severe swelling)", color = Color(0xFFD32F2F), fontSize = 13.sp, modifier = Modifier.padding(bottom = 4.dp))
                    Text("• Broken prosthesis with sharp edges cutting your mouth", color = Color(0xFFD32F2F), fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Emergency call */ },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_call), contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Emergency Contact", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* Schedule */ },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
            ) {
                Text("Schedule Check-up", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun WarningItemRow(title: String, description: String, status: String, badgeBg: Color, badgeText: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, modifier = Modifier.weight(1f), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(badgeBg).padding(horizontal = 8.dp, vertical = 2.dp)) {
                    Text(text = status, color = badgeText, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = description, color = Color.Gray, fontSize = 13.sp)
        }
    }
}
