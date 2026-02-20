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
fun EducationScreen(
    onBack: () -> Unit, 
    onDosAndDontsClick: () -> Unit,
    onWarningSignsClick: () -> Unit,
    onFAQClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Care Instructions", color = Color.White, fontSize = 20.sp) },
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
            // Header Guide Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = OnboardingTeal),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Prosthesis Care Guide", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Follow these instructions for optimal prosthesis maintenance and longevity",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            EducationItem(
                "Daily Cleaning",
                "Remove and rinse prosthesis after each meal. Use soft-bristled brush with denture cleaner.",
                R.drawable.ic_cleaning,
                Color(0xFFE8EAF6),
                Color(0xFF3F51B5)
            )

            EducationItem(
                "Gentle Handling",
                "Always handle over a soft towel or basin of water to prevent damage if dropped.",
                R.drawable.ic_sparkle,
                Color(0xFFE0F2F1),
                Color(0xFF009688)
            )

            EducationItem(
                "Overnight Care",
                "Soak prosthesis overnight in denture solution to maintain shape and cleanliness.",
                R.drawable.ic_nightlight,
                Color(0xFFF3E5F5),
                Color(0xFF9C27B0)
            )

            EducationItem(
                "Diet Considerations",
                "Avoid very hard or sticky foods. Cut food into smaller pieces for easier chewing.",
                R.drawable.ic_restaurant,
                Color(0xFFFFF3E0),
                Color(0xFFFF9800)
            )

            EducationItem(
                "Regular Check-ups",
                "Visit your dentist every 6 months for professional cleaning and adjustment.",
                R.drawable.ic_shield_check,
                Color(0xFFE8F5E9),
                Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = onDosAndDontsClick,
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
                ) {
                    Text("Do's & Don't's", fontSize = 14.sp)
                }
                Button(
                    onClick = onWarningSignsClick,
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
                ) {
                    Text("Warning Signs", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onFAQClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0))
            ) {
                Text("Frequently Asked Questions", color = DarkGrey)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EducationItem(title: String, description: String, icon: Int, bgColor: Color, iconColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = DarkGrey, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(description, color = LightGrey, fontSize = 13.sp)
            }
        }
    }
}
