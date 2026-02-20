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
fun PeriodicCareScreen(
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Weekly") }
    var task1Checked by remember { mutableStateOf(false) }
    var task2Checked by remember { mutableStateOf(false) }
    var task3Checked by remember { mutableStateOf(false) }

    val checkedCount = listOf(task1Checked, task2Checked, task3Checked).count { it }
    val progress = checkedCount / 3f

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Periodic Care", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tab Selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (selectedTab == "Weekly") OnboardingTeal else Color.Transparent)
                        .clickable { selectedTab = "Weekly" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Weekly Care",
                        color = if (selectedTab == "Weekly") Color.White else Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (selectedTab == "Monthly") OnboardingTeal else Color.Transparent)
                        .clickable { selectedTab = "Monthly" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Monthly Care",
                        color = if (selectedTab == "Monthly") Color.White else Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Progress Card
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
                                selectedTab,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF37474F)
                            )
                            Text(
                                "Every Sunday",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                "$checkedCount/3",
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
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Care Checklist",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F),
                modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp)
            )

            PeriodicChecklistItem(
                icon = R.drawable.ic_sparkle,
                text = "Deep clean with denture brush",
                isChecked = task1Checked,
                onCheckedChange = { task1Checked = it }
            )

            PeriodicChecklistItem(
                icon = R.drawable.ic_cleaning,
                text = "Soak overnight in cleaning tablet",
                isChecked = task2Checked,
                onCheckedChange = { task2Checked = it }
            )

            PeriodicChecklistItem(
                icon = R.drawable.ic_visibility,
                text = "Inspect for cracks or damage",
                isChecked = task3Checked,
                onCheckedChange = { task3Checked = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = checkedCount == 3,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OnboardingTeal,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFCFD8DC),
                    disabledContentColor = Color.Gray
                )
            ) {
                Text("Complete Weekly Care", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun PeriodicChecklistItem(
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
