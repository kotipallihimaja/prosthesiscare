package com.simats.prosthesiscare

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
import com.simats.prosthesiscare.ui.theme.DarkGrey
import com.simats.prosthesiscare.ui.theme.LightGrey
import com.simats.prosthesiscare.ui.theme.OnboardingTeal
import java.util.*
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProsthesisDetailsScreen(
    currentCategory: String,
    currentType: String,
    onBack: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var category by remember { mutableStateOf(currentCategory) }
    var type by remember { mutableStateOf(currentType) }
    var installationDate by remember { mutableStateOf("Aug 15, 2025") }
    var material by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                        installationDate = sdf.format(Date(it))
                    }
                    showDatePicker = false
                }) {
                    Text("OK", color = OnboardingTeal)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = OnboardingTeal)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Update Prosthesis Details", color = Color.White, fontSize = 20.sp) },
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
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_shield_check), contentDescription = null, tint = OnboardingTeal, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Prosthesis Information", color = OnboardingTeal, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))

                    DropdownSelector("Category *", category, listOf("Fixed", "Removable")) { 
                        category = it
                        type = "" // Reset type when category changes
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    val typeOptions = if (category == "Fixed") listOf("Crown", "Bridge", "Implant") else listOf("Full Denture", "Partial Denture")
                    DropdownSelector("Type *", type, typeOptions) { type = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Installation Date
                    Column {
                        Text("Installation Date", color = DarkGrey, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        OutlinedTextField(
                            value = installationDate,
                            onValueChange = { installationDate = it },
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            placeholder = { Text("Select Date", color = LightGrey) },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp).clickable { showDatePicker = true },
                                    tint = LightGrey
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OnboardingTeal, unfocusedBorderColor = Color(0xFFE0E0E0))
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    DropdownSelector("Material", material, listOf("Porcelain", "Ceramic", "Acrylic", "Metal", "Zirconia")) { material = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column {
                        Text("Notes (Optional)", color = DarkGrey, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            modifier = Modifier.fillMaxWidth().height(120.dp).padding(top = 4.dp),
                            placeholder = { Text("Any additional information...", color = LightGrey) },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OnboardingTeal, unfocusedBorderColor = Color(0xFFE0E0E0))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                    Text("💡", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Note: Updated information will be visible to your dentist and reflected in your profile.",
                        color = Color(0xFF1976D2),
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSave(category, type, installationDate, material) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = category.isNotEmpty() && type.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (category.isNotEmpty() && type.isNotEmpty()) OnboardingTeal else Color(0xFFCFD8DC))
            ) {
                Text("Save Changes", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(label: String, selectedValue: String, options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, color = DarkGrey, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
        ) {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                placeholder = { Text("Select option", color = LightGrey) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OnboardingTeal, unfocusedBorderColor = Color(0xFFE0E0E0))
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onSelect(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
