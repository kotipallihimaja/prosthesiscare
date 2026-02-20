package com.simats.prosthesiscare

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyMaintenanceScreen(
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    var task1Checked by remember { mutableStateOf(false) }
    var task2Checked by remember { mutableStateOf(false) }
    var task3Checked by remember { mutableStateOf(false) }
    var task4Checked by remember { mutableStateOf(false) }

    val checkedCount = listOf(task1Checked, task2Checked, task3Checked, task4Checked).count { it }
    val progress = checkedCount / 4f

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Daily Maintenance", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                },
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Morning Routine",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF37474F)
                            )
                            Text(
                                "9:00 AM Reminder",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                "$checkedCount/4",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnboardingTeal
                            )
                            Text(
                                "Tasks",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = OnboardingTeal,
                        trackColor = Color(0xFFE0E0E0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Complete all tasks to maintain your prosthesis properly",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Cleaning Checklist",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ChecklistItem(
                icon = R.drawable.ic_cleaning,
                text = "Remove and rinse prosthesis",
                isChecked = task1Checked,
                onCheckedChange = { task1Checked = it }
            )

            ChecklistItem(
                icon = R.drawable.ic_sparkle,
                text = "Brush prosthesis gently",
                isChecked = task2Checked,
                onCheckedChange = { task2Checked = it }
            )

            ChecklistItem(
                icon = R.drawable.ic_person,
                text = "Clean mouth and gums",
                isChecked = task3Checked,
                onCheckedChange = { task3Checked = it }
            )

            ChecklistItem(
                icon = R.drawable.ic_cleaning,
                text = "Soak in cleaning solution",
                isChecked = task4Checked,
                onCheckedChange = { task4Checked = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (checkedCount == 4) OnboardingTeal else Color(0xFFCFD8DC),
                    contentColor = Color.White
                )
            ) {
                Text("Mark as Complete", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { /* Snooze */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE))
            ) {
                Text("Snooze for 30 Minutes", color = Color(0xFF37474F), fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ChecklistItem(
    icon: Int,
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onCheckedChange(!isChecked) }
            .then(
                if (isChecked) Modifier.border(1.dp, OnboardingTeal, RoundedCornerShape(12.dp))
                else Modifier
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isChecked) Color(0xFFF1F8F7) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isChecked) OnboardingTeal else Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (isChecked) R.drawable.ic_check_circle else icon),
                    contentDescription = null,
                    tint = if (isChecked) Color.White else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                color = if (isChecked) OnboardingTeal else Color(0xFF37474F),
                textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
                fontWeight = if (isChecked) FontWeight.Medium else FontWeight.Normal
            )
        }
    }
}
