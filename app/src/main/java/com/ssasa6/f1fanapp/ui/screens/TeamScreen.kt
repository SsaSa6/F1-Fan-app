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
fun TeamScreen(selectedTeam: Team, modifier: Modifier = Modifier) {
    val accent   = selectedTeam.color
    val myDrivers = allDrivers.filter { it.teamId == selectedTeam.id }
    val constrPos = allTeams.indexOfFirst { it.id == selectedTeam.id } + 1
    val leadDriver = myDrivers.firstOrNull() ?: allDrivers.first()
    val form = recentForm[leadDriver.id] ?: listOf(10, 9, 11, 8, 12)

    LazyColumn(modifier = modifier.fillMaxSize().background(PwBg)) {
        item {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 18.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("MY TEAM", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.6.sp)
                Text("Edit ›", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PwTextSub)
            }
        }

        item {
            // Team Hero Card
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Brush.linearGradient(listOf(accent, PwSurface2)))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(20.dp))
            ) {
                Column(Modifier.padding(horizontal = 18.dp, vertical = 20.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                        Column {
                            Text(selectedTeam.name.uppercase(), fontWeight = FontWeight.Black, fontSize = 30.sp, letterSpacing = (-0.8).sp, lineHeight = 29.sp, color = PwText)
                            Text(selectedTeam.full, fontSize = 12.5.sp, color = Color(0xFFE8E8EC), modifier = Modifier.padding(top = 6.dp))
                        }
                        Box(
                            modifier = Modifier.size(52.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.16f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(selectedTeam.short, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.White)
                        }
                    }
                    Row(Modifier.padding(top = 18.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        listOf("P$constrPos" to "CONSTRUCTORS", "${selectedTeam.pts}" to "POINTS", "${selectedTeam.titles}" to "TITLES").forEach { (v, l) ->
                            Column {
                                Text(v, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.ExtraBold, fontSize = 26.sp, color = PwText)
                                Text(l, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = Color.White.copy(alpha = 0.7f), letterSpacing = 0.8.sp, modifier = Modifier.padding(top = 1.dp))
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
        }

        item {
            // Drivers
            Text("2026 DRIVERS", modifier = Modifier.padding(horizontal = 18.dp, bottom = 10.dp), fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = PwText)
            Row(Modifier.padding(horizontal = 14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                myDrivers.take(2).forEach { d ->
                    val dPos = allDrivers.indexOfFirst { it.id == d.id } + 1
                    Column(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(PwCard)
                            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(14.dp))
                            .padding(14.dp)
                    ) {
                        Text("#${d.number}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = accent)
                        Text(d.first, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = PwText, modifier = Modifier.padding(top = 6.dp), lineHeight = 16.sp)
                        Text(d.last, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = PwText, lineHeight = 16.sp)
                        Row(Modifier.padding(top = 10.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column {
                                Text("P$dPos", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PwText)
                                Text("POS", fontSize = 8.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim)
                            }
                            Column {
                                Text("${d.pts}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PwText)
                                Text("PTS", fontSize = 8.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim)
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
        }

        item {
            // Recent Form (lead driver)
            Text("RECENT FORM", modifier = Modifier.padding(horizontal = 18.dp), fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = PwText)
            Text("· ${leadDriver.last} · last 5", fontSize = 11.sp, color = PwTextDim, modifier = Modifier.padding(horizontal = 18.dp, bottom = 10.dp))
            Row(Modifier.padding(horizontal = 14.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                form.take(5).forEach { r ->
                    val isDnf = r is String
                    val isWin = r == 1
                    val isPod = r == 2 || r == 3
                    Box(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(11.dp))
                            .background(
                                when { isWin -> accent; isPod -> Color.White.copy(alpha = 0.13f); isDnf -> Color(0xFFE6002B).copy(alpha = 0.14f); else -> Color.White.copy(alpha = 0.05f) }
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$r", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = when { isWin -> Color.Black; isDnf -> Color(0xFFFF5C72); else -> Color(0xFFE8E8EC) })
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
        }

        item {
            // Car placeholder
            Text("CAR · 2026", modifier = Modifier.padding(horizontal = 18.dp, bottom = 10.dp), fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = PwText)
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp).fillMaxWidth().height(96.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PwCard)
                    .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("[ car render ]", fontFamily = FontFamily.Monospace, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PwTextFaint, letterSpacing = 1.sp)
            }
            Spacer(Modifier.height(18.dp))
        }

        item {
            // Team Info
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(PwCard)
                    .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(16.dp))
            ) {
                Column {
                    listOf(
                        "Power unit"      to selectedTeam.pu,
                        "Team principal"  to selectedTeam.tp,
                        "Headquarters"    to selectedTeam.base,
                        "First entry"     to selectedTeam.since.toString(),
                    ).forEachIndexed { i, (k, v) ->
                        if (i > 0) Divider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 13.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(k, fontSize = 12.5.sp, color = PwTextSub)
                            Text(v, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp, color = PwText, fontFamily = if (k == "First entry") FontFamily.Monospace else FontFamily.Default)
                        }
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
