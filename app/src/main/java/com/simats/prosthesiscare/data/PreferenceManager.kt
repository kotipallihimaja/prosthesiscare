package com.simats.prosthesiscare.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserData(
        name: String,
        email: String,
        phone: String,
        age: String,
        isHighRisk: Boolean,
        prosthesisCategory: String,
        prosthesisType: String
    ) {
        sharedPreferences.edit().apply {
            putString("name", name)
            putString("email", email)
            putString("phone", phone)
            putString("age", age)
            putBoolean("isHighRisk", isHighRisk)
            putString("prosthesisCategory", prosthesisCategory)
            putString("prosthesisType", prosthesisType)
            putBoolean("isRegistered", true)
            apply()
        }
    }

    fun getUserData(): UserData {
        return UserData(
            name = sharedPreferences.getString("name", "") ?: "",
            email = sharedPreferences.getString("email", "") ?: "",
            phone = sharedPreferences.getString("phone", "") ?: "",
            age = sharedPreferences.getString("age", "") ?: "",
            isHighRisk = sharedPreferences.getBoolean("isHighRisk", false),
            prosthesisCategory = sharedPreferences.getString("prosthesisCategory", "") ?: "",
            prosthesisType = sharedPreferences.getString("prosthesisType", "") ?: "",
            isRegistered = sharedPreferences.getBoolean("isRegistered", false)
        )
    }

    fun isRegistered(): Boolean {
        return sharedPreferences.getBoolean("isRegistered", false)
    }
    
    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}

data class UserData(
    val name: String,
    val email: String,
    val phone: String,
    val age: String,
    val isHighRisk: Boolean,
    val prosthesisCategory: String,
    val prosthesisType: String,
    val isRegistered: Boolean
)
