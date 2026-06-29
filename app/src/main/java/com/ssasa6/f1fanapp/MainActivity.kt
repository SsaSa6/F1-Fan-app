package com.ssasa6.f1fanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ssasa6.f1fanapp.ui.screens.ChampionshipScreen
import com.ssasa6.f1fanapp.ui.theme.F1Dark
import com.ssasa6.f1fanapp.ui.theme.F1FanAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            F1FanAppTheme {
                F1AppMain()
            }
        }
    }
}

private data class NavItem(val label: String, val icon: ImageVector)

@Composable
fun F1AppMain() {
    var selectedTab by remember { mutableStateOf(0) }

    val navItems = listOf(
        NavItem("Championship", Icons.Default.EmojiEvents),
        NavItem("Calendar", Icons.Default.DateRange),
        NavItem("News", Icons.Default.Article),
        NavItem("Profile", Icons.Default.Person),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label, fontSize = 11.sp) }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> ChampionshipScreen(modifier = Modifier.padding(innerPadding))
            else -> PlaceholderScreen(
                label = navItems[selectedTab].label,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(F1Dark),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
