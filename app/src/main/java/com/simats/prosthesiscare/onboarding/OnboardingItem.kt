package com.simats.prosthesiscare.onboarding

import androidx.annotation.DrawableRes
import com.simats.prosthesiscare.R

data class OnboardingItem(
    val title: String,
    val description: String,
    @DrawableRes val icon: Int,
    val backgroundColor: androidx.compose.ui.graphics.Color
)

val onboardingItems = listOf(
    OnboardingItem(
        title = "Never Miss Maintenance",
        description = "Get timely reminders for daily cleaning and regular maintenance of your prosthesis",
        icon = R.drawable.ic_notification, // Make sure to add these drawables
        backgroundColor = androidx.compose.ui.graphics.Color(0xFF2979FF)
    ),
    OnboardingItem(
        title = "Track Follow-Ups",
        description = "Schedule and manage your dental appointments with ease",
        icon = R.drawable.ic_calendar,
        backgroundColor = androidx.compose.ui.graphics.Color(0xFF00BFA5)
    ),
    OnboardingItem(
        title = "Learn Proper Care",
        description = "Access expert guidance and instructions for long-lasting prosthesis care",
        icon = R.drawable.ic_book,
        backgroundColor = androidx.compose.ui.graphics.Color(0xFF00B8D4)
    )
)
