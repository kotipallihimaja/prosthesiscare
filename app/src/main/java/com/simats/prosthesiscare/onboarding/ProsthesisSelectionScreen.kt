package com.simats.prosthesiscare.onboarding

import androidx.compose.foundation.BorderStroke
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
fun ProsthesisSelectionScreen(onBack: () -> Unit, onContinue: (String, String) -> Unit) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedType by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Your Prosthesis", color = Color.White, fontSize = 20.sp) },
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
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "What type of prosthesis do you have?",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGrey
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Please select the category and specific type of dental prosthesis you're using",
                        color = LightGrey,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Category", fontWeight = FontWeight.Bold, color = DarkGrey, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ProsthesisCategoryCard(
                    title = "Fixed",
                    subtitle = "Permanent attachment",
                    icon = R.drawable.ic_anchor,
                    isSelected = selectedCategory == "Fixed",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedCategory = "Fixed"
                    selectedType = null
                }

                ProsthesisCategoryCard(
                    title = "Removable",
                    subtitle = "Can be taken out",
                    icon = R.drawable.ic_sentiment_satisfied,
                    isSelected = selectedCategory == "Removable",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedCategory = "Removable"
                    selectedType = null
                }
            }

            if (selectedCategory != null) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Select $selectedCategory Type", fontWeight = FontWeight.Bold, color = DarkGrey, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))

                if (selectedCategory == "Fixed") {
                    ProsthesisTypeItem("Crown", "Cap covering a damaged tooth", R.drawable.ic_crown, Color(0xFFE3F2FD), Color(0xFF2196F3), selectedType == "Crown") { selectedType = "Crown" }
                    ProsthesisTypeItem("Bridge", "Fixed replacement for missing teeth", R.drawable.ic_bridge, Color(0xFFE0F2F1), Color(0xFF009688), selectedType == "Bridge") { selectedType = "Bridge" }
                    ProsthesisTypeItem("Implant", "Permanent tooth replacement", R.drawable.ic_implant, Color(0xFFF3E5F5), Color(0xFF9C27B0), selectedType == "Implant") { selectedType = "Implant" }
                } else {
                    ProsthesisTypeItem("Full Denture", "Complete set of replacement teeth", R.drawable.ic_sentiment_satisfied, Color(0xFFE8F5E9), Color(0xFF4CAF50), selectedType == "Full Denture") { selectedType = "Full Denture" }
                    ProsthesisTypeItem("Partial Denture", "Replaces some missing teeth", R.drawable.ic_sentiment_satisfied, Color(0xFFFFF3E0), Color(0xFFFF9800), selectedType == "Partial Denture") { selectedType = "Partial Denture" }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onContinue(selectedCategory!!, selectedType!!) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = selectedType != null,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedType != null) OnboardingTeal else Color(0xFFCFD8DC)
                )
            ) {
                Text("Continue to Dashboard", color = if (selectedType != null) Color.White else DarkGrey, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProsthesisCategoryCard(
    title: String,
    subtitle: String,
    icon: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(160.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = if (isSelected) BorderStroke(2.dp, OnboardingTeal) else BorderStroke(1.dp, Color(0xFFEEEEEE)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = if (isSelected) OnboardingTeal else Color(0xFF00A693)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, fontWeight = FontWeight.Bold, color = DarkGrey)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, color = LightGrey, fontSize = 11.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
fun ProsthesisTypeItem(
    title: String,
    description: String,
    icon: Int,
    bgColor: Color,
    iconColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = if (isSelected) bgColor.copy(alpha = 0.5f) else Color(0xFFF1F5F9).copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        border = if (isSelected) BorderStroke(1.dp, iconColor) else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, color = DarkGrey)
                Text(text = description, color = LightGrey, fontSize = 13.sp)
            }
        }
    }
}
