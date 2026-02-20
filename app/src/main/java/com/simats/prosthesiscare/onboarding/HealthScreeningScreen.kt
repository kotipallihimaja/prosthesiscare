package com.simats.prosthesiscare.onboarding

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.R
import com.simats.prosthesiscare.ui.theme.DarkGrey
import com.simats.prosthesiscare.ui.theme.LightGrey
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreeningScreen(onBack: () -> Unit, onContinue: (Boolean) -> Unit) {
    val conditions = remember {
        mutableStateMapOf(
            "Diabetes" to false,
            "Hypertension" to false,
            "Hyperthyroidism" to false,
            "Hypothyroidism" to false,
            "Heart Disease" to false,
            "Blood Disorder" to false,
            "Immune Disorder" to false,
            "Osteoporosis" to false
        )
    }

    val isHighRisk = conditions.values.any { it }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health Screening", color = Color.White, fontSize = 20.sp) },
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
                .background(Color(0xFFF4F9F9))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2979FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_warning), contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Health Screening", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Required for personalized care", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4)), // Light Yellow
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Important: Please answer honestly.\nThis information helps us provide better care and monitor your dental health more effectively.",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF8D6E63),
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Do you have any of the following conditions?", fontWeight = FontWeight.SemiBold, color = DarkGrey)

            Spacer(modifier = Modifier.height(16.dp))

            HealthConditionItem("Diabetes", "High blood sugar", R.drawable.ic_pulse) { conditions["Diabetes"] = it }
            HealthConditionItem("Hypertension", "High blood pressure", R.drawable.ic_heart) { conditions["Hypertension"] = it }
            HealthConditionItem("Hyperthyroidism", "Overactive thyroid", R.drawable.ic_pulse) { conditions["Hyperthyroidism"] = it }
            HealthConditionItem("Hypothyroidism", "Underactive thyroid", R.drawable.ic_pulse) { conditions["Hypothyroidism"] = it }
            HealthConditionItem("Heart Disease", "Cardiovascular conditions", R.drawable.ic_heart) { conditions["Heart Disease"] = it }
            HealthConditionItem("Blood Disorder", "Clotting or bleeding issues", R.drawable.ic_pulse) { conditions["Blood Disorder"] = it }
            HealthConditionItem("Immune Disorder", "Weakened immunity", R.drawable.ic_pulse) { conditions["Immune Disorder"] = it }
            HealthConditionItem("Osteoporosis", "Bone density loss", R.drawable.ic_pulse) { conditions["Osteoporosis"] = it }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onContinue(isHighRisk) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal)
            ) {
                Text("Continue to Prosthesis Selection", color = Color.White, fontSize = 15.sp)
            }
        }
    }
}

@Composable
fun HealthConditionItem(title: String, subtitle: String, icon: Int, onOptionSelected: (Boolean) -> Unit) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFE3F2FD)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color(0xFF2196F3), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = DarkGrey)
                    Text(subtitle, color = LightGrey, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ConditionButton("Yes", selectedOption == "Yes", Modifier.weight(1f)) { 
                    selectedOption = "Yes"
                    onOptionSelected(true)
                }
                ConditionButton("No", selectedOption == "No", Modifier.weight(1f)) { 
                    selectedOption = "No"
                    onOptionSelected(false)
                }
            }
        }
    }
}

@Composable
fun ConditionButton(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) OnboardingTeal.copy(alpha = 0.1f) else Color(0xFFF5F5F5),
            contentColor = if (isSelected) OnboardingTeal else DarkGrey
        ),
        elevation = null,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text, fontSize = 14.sp)
    }
}
