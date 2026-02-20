package com.simats.prosthesiscare

import androidx.compose.animation.*
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
fun FAQScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FAQ & Help", color = Color.White, fontSize = 20.sp) },
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
            // Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2979FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Frequently Asked Questions", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Find answers to common questions about prosthesis care", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FAQItem("How often should I clean my prosthesis?", "You should clean your prosthesis at least once a day, and preferably after every meal.")
            FAQItem("Can I sleep with my dentures?", "It is generally recommended to remove dentures at night to give your gums a rest and prevent fungal infections.")
            FAQItem("How long do prostheses typically last?", "With proper care, most prostheses last 5-7 years before they need to be replaced or relined.")
            FAQItem("What type of cleaning products should I use?", "Use specialized denture cleaners or mild dish soap. Avoid regular toothpaste as it can be too abrasive.")
            FAQItem("Why does my prosthesis feel loose?", "Over time, gums and bone can change shape. If it feels loose, see your dentist for an adjustment or reline.")
            FAQItem("How do I prevent staining?", "Avoid dark liquids like coffee, tea, and red wine. Clean promptly after eating pigmented foods.")
            FAQItem("What foods should I avoid?", "Avoid extremely sticky foods (like taffy) and very hard foods (like ice or hard nuts).")
            FAQItem("How often should I visit the dentist?", "Visit your dentist every 6 months for professional cleaning and a check-up of your oral tissues.")

            Spacer(modifier = Modifier.height(24.dp))

            // Footer Contact Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Still have questions?", color = Color(0xFF00796B), fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { /* Support call */ },
                        colors = ButtonDefaults.buttonColors(containerColor = OnboardingTeal),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Contact Support")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = question, modifier = Modifier.weight(1f), fontSize = 15.sp, color = DarkGrey, fontWeight = FontWeight.Medium)
                Icon(
                    painter = painterResource(id = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = LightGrey,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = Color(0xFFEEEEEE))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = answer, fontSize = 14.sp, color = LightGrey, lineHeight = 20.sp)
                }
            }
        }
    }
}
