package com.ssasa6.f1fanapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.ssasa6.f1fanapp.data.*
import com.ssasa6.f1fanapp.ui.theme.*

@Composable
fun StandingsScreen(accent: Color, modifier: Modifier = Modifier) {
    var tab by remember { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize().background(PwBg)) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Text("CHAMPIONSHIP · 2026", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.6.sp)
            Text("STANDINGS", fontWeight = FontWeight.Black, fontSize = 25.sp, color = PwText, letterSpacing = (-0.6).sp)
            // Drivers / Constructors pill toggle
            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White.copy(alpha = 0.05f))
                    .padding(3.dp)
            ) {
                listOf("DRIVERS", "CONSTRUCTORS").forEachIndexed { i, label ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (tab == i) accent else Color.Transparent)
                            .clickable { tab = i }
                            .padding(vertical = 7.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(label, fontWeight = if (tab == i) FontWeight.Bold else FontWeight.SemiBold, fontSize = 12.5.sp, color = if (tab == i) Color.Black else PwTextSub, letterSpacing = 0.3.sp)
                    }
                }
            }
        }

        if (tab == 0) DriverList(accent) else ConstructorList(accent)
    }
}

@Composable
private fun DriverList(accent: Color) {
    val leaderPts = allDrivers.first().pts
    LazyColumn(contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp)) {
        itemsIndexed(allDrivers) { i, d ->
            val teamColor = teamMap[d.teamId]?.color ?: accent
            val pos = i + 1
            val gap = if (i == 0) "LEADER" else "−${leaderPts - d.pts}"
            Column {
                if (i > 0) Divider(Modifier.padding(horizontal = 4.dp), color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(11.dp)
                ) {
                    Text("$pos".padStart(2, '0'), fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = PwTextMuted, modifier = Modifier.width(20.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Box(Modifier.width(4.dp).height(32.dp).clip(RoundedCornerShape(2.dp)).background(teamColor))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("${d.first} ${d.last}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PwText)
                            Text(d.nat, fontFamily = FontFamily.Monospace, fontSize = 9.5.sp, color = PwTextFaint)
                        }
                        Text(teamMap[d.teamId]?.name ?: "", fontSize = 10.5.sp, color = PwTextMuted)
                    }
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.width(58.dp)) {
                        Text("${d.pts}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = PwText)
                        Text(gap, fontFamily = FontFamily.Monospace, fontSize = 9.5.sp, color = PwTextDim)
                    }
                }
            }
        }
        item { Spacer(Modifier.height(8.dp)) }
    }
}

@Composable
private fun ConstructorList(accent: Color) {
    val leaderPts = allTeams.first().pts
    LazyColumn(contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp)) {
        itemsIndexed(allTeams) { i, t ->
            val drivers = allDrivers.filter { it.teamId == t.id }
            val barPct = if (leaderPts > 0) (t.pts.toFloat() / leaderPts).coerceIn(0.06f, 1f) else 0f
            Column {
                if (i > 0) Divider(Modifier.padding(horizontal = 4.dp), color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("${i + 1}".padStart(2, '0'), fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = PwTextMuted, modifier = Modifier.width(20.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Box(Modifier.width(9.dp).height(38.dp).clip(RoundedCornerShape(3.dp)).background(t.color))
                    Column(Modifier.weight(1f)) {
                        Text(t.name.uppercase(), fontWeight = FontWeight.Bold, fontSize = 14.5.sp, color = PwText)
                        Text(drivers.joinToString(" · ") { it.last }, fontSize = 10.5.sp, color = PwTextMuted)
                        Spacer(Modifier.height(7.dp))
                        Box(Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)).background(Color.White.copy(alpha = 0.07f))) {
                            Box(Modifier.fillMaxWidth(barPct).height(4.dp).clip(RoundedCornerShape(2.dp)).background(t.color))
                        }
                    }
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.width(48.dp)) {
                        Text("${t.pts}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = PwText)
                        Text("PTS", fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim, letterSpacing = 0.5.sp)
                    }
                }
            }
        }
        item { Spacer(Modifier.height(8.dp)) }
    }
}
