package com.simats.prosthesiscare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import com.simats.prosthesiscare.ui.theme.OnboardingTeal

@Composable
fun HomeScreen(
    userName: String,
    email: String,
    phone: String,
    age: String,
    isHighRisk: Boolean,
    prosthesisCategory: String,
    prosthesisType: String,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("home") }
    var currentSubScreen by remember { mutableStateOf<String?>(null) }
    
    // Dynamic data states that can be updated
    var currentName by remember { mutableStateOf(userName) }
    var currentEmail by remember { mutableStateOf(email) }
    var currentPhone by remember { mutableStateOf(phone) }
    var currentAge by remember { mutableStateOf(age) }
    
    var currentCategory by remember { mutableStateOf(prosthesisCategory) }
    var currentType by remember { mutableStateOf(prosthesisType) }

    var isDailyCleaningCompleted by remember { mutableStateOf(false) }
    var isDeepCleaningCompleted by remember { mutableStateOf(false) }
    
    val primaryColor = if (isHighRisk) Color(0xFFE53935) else OnboardingTeal
    
    Scaffold(
        bottomBar = {
            if (currentSubScreen == null) {
                BottomNavigationBar(primaryColor, selectedTab) { 
                    selectedTab = it 
                    currentSubScreen = null
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                "home" -> {
                    when (currentSubScreen) {
                        "notifications" -> NotificationScreen(
                            onBack = { currentSubScreen = null },
                            onSettingsClick = { currentSubScreen = "reminder_settings" }
                        )
                        "reminder_settings" -> ReminderSettingsScreen(
                            onBack = { currentSubScreen = "notifications" },
                            onSave = { currentSubScreen = "notifications" }
                        )
                        "daily_maintenance" -> DailyMaintenanceScreen(
                            onBack = { currentSubScreen = null },
                            onComplete = { currentSubScreen = "maintenance_complete" }
                        )
                        "deep_cleaning" -> PeriodicCareScreen(
                            onBack = { currentSubScreen = null },
                            onComplete = { currentSubScreen = "maintenance_complete_periodic" }
                        )
                        "maintenance_complete" -> MaintenanceCompleteScreen(
                            onBackToDashboard = { 
                                isDailyCleaningCompleted = true
                                currentSubScreen = null 
                            },
                            onViewHistory = { 
                                isDailyCleaningCompleted = true
                                currentSubScreen = null
                                selectedTab = "reminders"
                            }
                        )
                        "maintenance_complete_periodic" -> MaintenanceCompleteScreen(
                            onBackToDashboard = { 
                                isDeepCleaningCompleted = true
                                currentSubScreen = null 
                            },
                            onViewHistory = { 
                                isDeepCleaningCompleted = true
                                currentSubScreen = null
                                selectedTab = "reminders"
                            }
                        )
                        "prosthesis_overview" -> ProsthesisOverviewScreen(
                            category = currentCategory,
                            type = currentType,
                            onBack = { currentSubScreen = null },
                            onUpdateClick = { currentSubScreen = "update_prosthesis" }
                        )
                        "update_prosthesis" -> UpdateProsthesisDetailsScreen(
                            currentCategory = currentCategory,
                            currentType = currentType,
                            onBack = { currentSubScreen = "prosthesis_overview" },
                            onSave = { category, type, date, material ->
                                currentCategory = category
                                currentType = type
                                currentSubScreen = "prosthesis_overview"
                            }
                        )
                        else -> HomeContent(
                            userName = currentName,
                            primaryColor = primaryColor,
                            isDailyCleaningCompleted = isDailyCleaningCompleted,
                            isDeepCleaningCompleted = isDeepCleaningCompleted,
                            onDailyCleaningClick = { currentSubScreen = "daily_maintenance" },
                            onDeepCleaningClick = { currentSubScreen = "deep_cleaning" },
                            onProscareClick = { currentSubScreen = "prosthesis_overview" },
                            onLearnCareClick = { selectedTab = "education" },
                            onHistoryClick = { selectedTab = "reminders" },
                            onNotificationClick = { currentSubScreen = "notifications" },
                            onWarningSignsClick = { 
                                selectedTab = "education"
                                currentSubScreen = "warning_signs"
                            }
                        )
                    }
                }
                "appointments" -> {
                    when (currentSubScreen) {
                        "history" -> AppointmentHistoryScreen(onBack = { currentSubScreen = null })
                        "details" -> AppointmentDetailsScreen(
                            onBack = { currentSubScreen = null },
                            onConfirm = { 
                                currentSubScreen = null
                                selectedTab = "home"
                            }
                        )
                        else -> AppointmentScreen(
                            onBack = { selectedTab = "home" },
                            onViewHistory = { currentSubScreen = "history" },
                            onViewDetails = { currentSubScreen = "details" }
                        )
                    }
                }
                "education" -> {
                    when (currentSubScreen) {
                        "dos_and_donts" -> DosAndDontsScreen(onBack = { currentSubScreen = null })
                        "warning_signs" -> WarningSignsScreen(onBack = { currentSubScreen = null })
                        "faq" -> FAQScreen(onBack = { currentSubScreen = null })
                        else -> EducationScreen(
                            onBack = { selectedTab = "home" },
                            onDosAndDontsClick = { currentSubScreen = "dos_and_donts" },
                            onWarningSignsClick = { currentSubScreen = "warning_signs" },
                            onFAQClick = { currentSubScreen = "faq" }
                        )
                    }
                }
                "reminders" -> ReminderHistoryScreen(onBack = { selectedTab = "home" })
                "profile" -> {
                    when (currentSubScreen) {
                        "edit_profile" -> EditProfileScreen(
                            initialName = currentName,
                            initialEmail = currentEmail,
                            initialPhone = currentPhone,
                            initialAge = currentAge,
                            onBack = { currentSubScreen = null },
                            onSave = { name, email, phone, age ->
                                currentName = name
                                currentEmail = email
                                currentPhone = phone
                                currentAge = age
                                currentSubScreen = null
                            }
                        )
                        "update_prosthesis_profile" -> UpdateProsthesisDetailsScreen(
                            currentCategory = currentCategory,
                            currentType = currentType,
                            onBack = { currentSubScreen = null },
                            onSave = { category, type, date, material ->
                                currentCategory = category
                                currentType = type
                                currentSubScreen = null
                            }
                        )
                        "language_accessibility" -> LanguageAccessibilityScreen(
                            onBack = { currentSubScreen = null },
                            onSave = { currentSubScreen = null }
                        )
                        "settings" -> AccountSettingsScreen(
                            userName = currentName,
                            onBack = { currentSubScreen = null },
                            onLogout = onLogout
                        )
                        else -> ProfileScreen(
                            userName = currentName,
                            email = currentEmail,
                            phone = currentPhone,
                            age = currentAge,
                            prosthesisCategory = currentCategory,
                            prosthesisType = currentType,
                            isHighRisk = isHighRisk,
                            onBack = { selectedTab = "home" },
                            onEditClick = { currentSubScreen = "edit_profile" },
                            onProsthesisDetailsClick = { currentSubScreen = "update_prosthesis_profile" },
                            onLanguageClick = { currentSubScreen = "language_accessibility" },
                            onLogout = onLogout
                        )
                    }
                }
                else -> HomeContent(
                    userName = currentName,
                    primaryColor = primaryColor,
                    isDailyCleaningCompleted = isDailyCleaningCompleted,
                    isDeepCleaningCompleted = isDeepCleaningCompleted,
                    onDailyCleaningClick = { currentSubScreen = "daily_maintenance" },
                    onDeepCleaningClick = { currentSubScreen = "deep_cleaning" },
                    onProscareClick = {},
                    onLearnCareClick = { selectedTab = "education" },
                    onHistoryClick = { selectedTab = "reminders" },
                    onNotificationClick = { currentSubScreen = "notifications" },
                    onWarningSignsClick = {
                        selectedTab = "education"
                        currentSubScreen = "warning_signs"
                    }
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    userName: String, 
    primaryColor: Color, 
    isDailyCleaningCompleted: Boolean,
    isDeepCleaningCompleted: Boolean,
    onDailyCleaningClick: () -> Unit = {},
    onDeepCleaningClick: () -> Unit = {},
    onProscareClick: () -> Unit,
    onLearnCareClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onWarningSignsClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9F9))
            .verticalScroll(scrollState)
    ) {
        Header(userName, primaryColor, onNotificationClick)
        Body(
            primaryColor, 
            isDailyCleaningCompleted,
            isDeepCleaningCompleted,
            onDailyCleaningClick, 
            onDeepCleaningClick,
            onProscareClick, 
            onLearnCareClick, 
            onHistoryClick, 
            onWarningSignsClick
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun Header(userName: String, primaryColor: Color, onNotificationClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(primaryColor)
            .padding(24.dp)
    ) {
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Welcome back,\n$userName",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications_none), 
                    contentDescription = null, 
                    tint = Color.White,
                    modifier = Modifier.clickable { onNotificationClick() }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.3f))
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(text = "Compliance Score", color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.85f },
                        modifier = Modifier.fillMaxWidth().height(8.dp), 
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "85%", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.End))
                }
            }
        }
    }
}

@Composable
fun Body(
    primaryColor: Color, 
    isDailyCleaningCompleted: Boolean,
    isDeepCleaningCompleted: Boolean,
    onDailyCleaningClick: () -> Unit,
    onDeepCleaningClick: () -> Unit,
    onProscareClick: () -> Unit,
    onLearnCareClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onWarningSignsClick: () -> Unit
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "Today's Reminders", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        ReminderCard(
            "Daily Cleaning", 
            if (isDailyCleaningCompleted) "9:00 AM • Completed" else "9:00 AM • Not completed", 
            isDailyCleaningCompleted, 
            primaryColor, 
            onDailyCleaningClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        ReminderCard(
            "Deep Cleaning", 
            if (isDeepCleaningCompleted) "Weekly • Completed" else "Weekly • Due today", 
            isDeepCleaningCompleted, 
            primaryColor,
            onDeepCleaningClick
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Upcoming Appointments", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        AppointmentCard(primaryColor)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        ActionButtons(primaryColor, onProscareClick, onLearnCareClick, onHistoryClick, onWarningSignsClick)
    }
}

@Composable
fun ReminderCard(title: String, subtitle: String, isCompleted: Boolean, primaryColor: Color, onClick: () -> Unit = {}) {
    val checkColor = if (isCompleted) Color(0xFF4CAF50) else Color.LightGray
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }, 
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_circle), 
                contentDescription = null, 
                tint = checkColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = subtitle, color = Color.Gray)
            }
        }
    }
}

@Composable
fun AppointmentCard(primaryColor: Color) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null, tint = primaryColor)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "6-Month Check-up", fontWeight = FontWeight.Bold)
                Text(text = "Feb 5, 2026 at 10:30 AM", color = Color.Gray)
                Text(text = "Dr. Sarah Johnson", color = primaryColor, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ActionButtons(
    primaryColor: Color, 
    onProscareClick: () -> Unit,
    onLearnCareClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onWarningSignsClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        ActionButton(
            title = "Prosthesis Care", 
            icon = R.drawable.ic_shield_check, 
            color = Color(0xFF2979FF), 
            modifier = Modifier.weight(1f),
            onClick = onProscareClick
        )
        ActionButton(
            title = "Learn Care", 
            icon = R.drawable.ic_book, 
            color = primaryColor, 
            modifier = Modifier.weight(1f),
            onClick = onLearnCareClick
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        ActionButton(
            title = "History", 
            icon = R.drawable.ic_history, 
            color = Color(0xFF00B8D4), 
            modifier = Modifier.weight(1f),
            onClick = onHistoryClick
        )
        ActionButton(
            title = "Warning Signs", 
            icon = R.drawable.ic_warning, 
            color = Color(0xFFFF5252), 
            modifier = Modifier.weight(1f),
            onClick = onWarningSignsClick
        )
    }
}

@Composable
fun ActionButton(title: String, icon: Int, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier.height(100.dp).clickable { onClick() }, 
        shape = RoundedCornerShape(12.dp), 
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BottomNavigationBar(primaryColor: Color, selectedTab: String, onTabSelected: (String) -> Unit) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = selectedTab == "home", 
            onClick = { onTabSelected("home") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = primaryColor.copy(alpha = 0.1f), selectedIconColor = primaryColor, selectedTextColor = primaryColor)
        )
        NavigationBarItem(
            selected = selectedTab == "appointments", 
            onClick = { onTabSelected("appointments") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "Appointments") },
            label = { Text("Appointments") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = primaryColor.copy(alpha = 0.1f), selectedIconColor = primaryColor, selectedTextColor = primaryColor)
        )
        NavigationBarItem(
            selected = selectedTab == "education", 
            onClick = { onTabSelected("education") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_education), contentDescription = "Education") },
            label = { Text("Education") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = primaryColor.copy(alpha = 0.1f), selectedIconColor = primaryColor, selectedTextColor = primaryColor)
        )
        NavigationBarItem(
            selected = selectedTab == "reminders", 
            onClick = { onTabSelected("reminders") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_notifications_none), contentDescription = "Reminders") },
            label = { Text("Reminders") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = primaryColor.copy(alpha = 0.1f), selectedIconColor = primaryColor, selectedTextColor = primaryColor)
        )
        NavigationBarItem(
            selected = selectedTab == "profile", 
            onClick = { onTabSelected("profile") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = primaryColor.copy(alpha = 0.1f), selectedIconColor = primaryColor, selectedTextColor = primaryColor)
        )
    }
}
