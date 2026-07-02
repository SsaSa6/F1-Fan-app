package com.ssasa6.f1fanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssasa6.f1fanapp.data.*
import com.ssasa6.f1fanapp.ui.appStrings
import com.ssasa6.f1fanapp.ui.screens.*
import com.ssasa6.f1fanapp.ui.theme.*

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
    var selectedTab    by remember { mutableStateOf(0) }
    var selectedTeamId by remember { mutableStateOf("mclaren") }
    var isKorean       by remember { mutableStateOf(false) }
    var selectedRace   by remember { mutableStateOf<com.ssasa6.f1fanapp.data.Race?>(null) }

    val selectedTeam = teamMap[selectedTeamId] ?: allTeams.first()
    val accent = selectedTeam.color
    val s = appStrings(isKorean)

    // Race detail screen (full-screen overlay, no bottom nav)
    val race = selectedRace
    if (race != null) {
        RaceWeekendScreen(
            race = race,
            accent = accent,
            isKorean = isKorean,
            onBack = { selectedRace = null },
        )
        return
    }

    val navItems = listOf(
        NavItem(s.navHome,      Icons.Default.Home),
        NavItem(s.navRaces,     Icons.Default.DateRange),
        NavItem(s.navStandings, Icons.Default.EmojiEvents),
        NavItem(s.navNews,      Icons.Default.Article),
        NavItem(s.navMyTeam,    Icons.Default.Person),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PwBg,
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth().background(PwNavBg)) {
                Divider(color = Color.White.copy(alpha = 0.06f), thickness = 1.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 6.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    navItems.forEachIndexed { index, item ->
                        val selected = selectedTab == index
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedTab = index }
                                .padding(vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(item.icon, contentDescription = item.label,
                                tint = if (selected) accent else PwNavInactive,
                                modifier = Modifier.size(22.dp))
                            Text(item.label, fontSize = 10.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
                                color = if (selected) accent else PwNavInactive)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        val bottomPad = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        when (selectedTab) {
            0 -> HomeScreen(
                selectedTeam = selectedTeam,
                onTeamSelected = { selectedTeamId = it },
                isKorean = isKorean,
                onLanguageToggle = { isKorean = !isKorean },
                modifier = bottomPad
            )
            1 -> RacesScreen(accent = accent, isKorean = isKorean, onRaceClick = { selectedRace = it }, modifier = bottomPad)
            2 -> StandingsScreen(accent = accent, isKorean = isKorean, modifier = bottomPad)
            3 -> NewsScreen(accent = accent, isKorean = isKorean, modifier = bottomPad)
            4 -> TeamScreen(selectedTeam = selectedTeam, isKorean = isKorean, modifier = bottomPad)
        }
    }
}
