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
fun DosAndDontsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Do's and Don'ts", color = Color.White, fontSize = 20.sp) },
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
            // Do's Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32)), // Green
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_check_circle), contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Do's", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("Follow these best practices", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    listOf(
                        "Clean your prosthesis daily with a soft brush",
                        "Remove prosthesis before sleeping",
                        "Soak in denture cleaning solution overnight",
                        "Handle with care over a soft surface",
                        "Visit dentist every 6 months",
                        "Rinse after every meal",
                        "Massage gums gently daily",
                        "Store in water when not wearing"
                    ).forEach { text ->
                        GuidelineItem(text, R.drawable.ic_check_circle, Color(0xFF4CAF50))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Don'ts Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC62828)), // Red
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_cancel), contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Don'ts", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("Avoid these common mistakes", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    listOf(
                        "Use hot water for cleaning",
                        "Use regular toothpaste (too abrasive)",
                        "Wear damaged or cracked prosthesis",
                        "Sleep with dentures unless advised",
                        "Use sharp objects for cleaning",
                        "Skip professional check-ups",
                        "Eat very hard or sticky foods",
                        "Try to repair broken prosthesis yourself"
                    ).forEach { text ->
                        GuidelineItem(text, R.drawable.ic_cancel, Color(0xFFF44336))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer info
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "💡 Following these guidelines will help extend the life of your prosthesis and maintain oral health",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF1976D2),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun GuidelineItem(text: String, icon: Int, tint: Color) {
    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = icon), contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = DarkGrey, fontSize = 14.sp)
    }
}
