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
fun RacesScreen(accent: Color, modifier: Modifier = Modifier) {
    val done = raceCalendar.count { it.status == "done" }
    val total = raceCalendar.size
    val pct   = done.toFloat() / total

    Column(modifier = modifier.fillMaxSize().background(PwBg)) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                Column {
                    Text("2026 SEASON", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.6.sp)
                    Text("RACE CALENDAR", fontWeight = FontWeight.Black, fontSize = 25.sp, color = PwText, letterSpacing = (-0.6).sp, modifier = Modifier.padding(top = 3.dp))
                }
                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("$done", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 19.sp, color = PwText)
                        Text("/$total", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 19.sp, color = PwTextFaint)
                    }
                    Text("ROUNDS DONE", fontSize = 8.5.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim, letterSpacing = 1.sp)
                }
            }
            // Progress bar
            Box(
                modifier = Modifier.padding(top = 12.dp).fillMaxWidth().height(5.dp).clip(RoundedCornerShape(3.dp)).background(Color.White.copy(alpha = 0.08f))
            ) {
                Box(Modifier.fillMaxWidth(pct).height(5.dp).clip(RoundedCornerShape(3.dp)).background(accent))
            }
        }

        // Calendar list
        LazyColumn(contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(PwCard)
                        .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(16.dp))
                ) {
                    Column {
                        raceCalendar.forEachIndexed { i, r ->
                            val isNext = r.status == "next"
                            val isDone = r.status == "done"
                            val statusColor = when (r.status) {
                                "next" -> accent
                                "done" -> Color(0xFFC9C9CF)
                                else   -> PwTextFaint
                            }
                            if (i > 0) Divider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (isNext) Color.White.copy(alpha = 0.05f) else Color.Transparent)
                                    .drawWithCache {
                                        onDrawBehind {
                                            if (isNext) drawRect(accent, size = androidx.compose.ui.geometry.Size(3.dp.toPx(), size.height))
                                        }
                                    }
                                    .padding(start = if (isNext) 11.dp else 14.dp, top = 11.dp, bottom = 11.dp, end = 14.dp)
                                    .graphicsLayer(alpha = if (isDone) 0.55f else 1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(r.round, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = PwTextMuted, modifier = Modifier.width(24.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                                Text(r.code, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = PwTextSub, letterSpacing = 0.5.sp, modifier = Modifier.width(34.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(r.gp, fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = PwText)
                                    Text("${r.circuit} · ${r.date}", fontSize = 10.sp, color = PwTextMuted)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(if (isDone) r.winner else if (isNext) "NEXT" else "—", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = statusColor)
                                    if (isDone) Text("WINNER", fontSize = 8.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim, letterSpacing = 0.5.sp)
                                    else if (isNext) Text("Jul 5", fontSize = 8.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim)
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
