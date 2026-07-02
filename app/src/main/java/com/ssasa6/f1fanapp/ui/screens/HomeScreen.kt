package com.ssasa6.f1fanapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.ssasa6.f1fanapp.data.*
import com.ssasa6.f1fanapp.ui.appStrings
import com.ssasa6.f1fanapp.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    selectedTeam: Team,
    onTeamSelected: (String) -> Unit,
    isKorean: Boolean,
    onLanguageToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val s       = appStrings(isKorean)
    val accent  by animateColorAsState(selectedTeam.color,  animationSpec = tween(350), label = "accent")
    val accent2 by animateColorAsState(selectedTeam.color2, animationSpec = tween(350), label = "accent2")

    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) { while (true) { delay(1000); now = System.currentTimeMillis() } }

    val targetMs = remember {
        java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC")).apply {
            set(2026, 6, 5, 14, 0, 0); set(java.util.Calendar.MILLISECOND, 0)
        }.timeInMillis
    }
    val diff = maxOf(0L, targetMs - now)
    val cdD  = diff / 86400000L
    val cdH  = (diff % 86400000L) / 3600000L
    val cdM  = (diff % 3600000L) / 60000L
    val cdS  = (diff % 60000L) / 1000L
    fun pad(n: Long) = n.toString().padStart(2, '0')

    val top3      = allDrivers.take(3)
    val myDrivers = allDrivers.filter { it.teamId == selectedTeam.id }
    val constrPos = allTeams.indexOfFirst { it.id == selectedTeam.id } + 1

    LazyColumn(modifier = modifier.fillMaxSize().background(PwBg)) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(accent.copy(alpha = 0.30f), Color.Transparent)))
                    .statusBarsPadding()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("F1 FAN", fontWeight = FontWeight.Black, fontSize = 23.sp, color = PwText)
                    Box(modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(accent).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("'26", fontWeight = FontWeight.Bold, fontSize = 11.sp, fontFamily = FontFamily.Monospace, color = Color.Black)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(7.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(7.dp))
                            .clickable { onLanguageToggle() }
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(if (isKorean) "한" else "EN", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = accent)
                    }
                    Box(modifier = Modifier.size(34.dp).border(1.dp, Color.White.copy(alpha = 0.12f), CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFFC9C9CF), modifier = Modifier.size(17.dp))
                    }
                    Box(modifier = Modifier.size(34.dp).background(Brush.linearGradient(listOf(accent, accent2)), CircleShape))
                }
            }
        }

        item {
            Text(s.pickYourTeam, modifier = Modifier.padding(start = 18.dp, bottom = 9.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PwTextDim, letterSpacing = 1.6.sp)
            LazyRow(contentPadding = PaddingValues(horizontal = 18.dp, vertical = 2.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(allTeams) { team ->
                    val sel = team.id == selectedTeam.id
                    val chipBg  by animateColorAsState(if (sel) team.color else Color.Transparent, tween(250), label = "chipBg")
                    val chipTxt by animateColorAsState(if (sel) Color.Black else PwText,           tween(250), label = "chipTxt")
                    val chipDot by animateColorAsState(if (sel) Color.Black else team.color,       tween(250), label = "chipDot")
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(11.dp))
                            .border(1.5.dp, team.color, RoundedCornerShape(11.dp))
                            .background(chipBg)
                            .clickable { onTeamSelected(team.id) }
                            .padding(horizontal = 13.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        Box(Modifier.size(7.dp).background(chipDot, CircleShape))
                        Text(team.short, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, color = chipTxt, letterSpacing = 0.3.sp)
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        item {
            Box(
                modifier = Modifier.padding(horizontal = 14.dp).clip(RoundedCornerShape(20.dp))
                    .background(Brush.linearGradient(listOf(PwSurface, PwSurface2)))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(20.dp))
            ) {
                Column {
                    Box(Modifier.fillMaxWidth().height(4.dp).background(accent))
                    Column(Modifier.padding(horizontal = 18.dp, vertical = 15.dp)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                PulseDot(accent)
                                Text("${s.nextRaceLabel} · ${s.roundLabel} 11", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.4.sp)
                            }
                            Text("GBR", fontFamily = FontFamily.Monospace, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PwTextMuted)
                        }
                        Text(
                            if (isKorean) "영국\n그랑프리" else "BRITISH\nGRAND PRIX",
                            fontWeight = FontWeight.Black, fontSize = 28.sp, lineHeight = 27.sp, letterSpacing = (-0.6).sp, color = PwText, modifier = Modifier.padding(top = 11.dp)
                        )
                        Text("Silverstone Circuit · 52 ${s.lapsLabel} · 5.891 km", fontSize = 12.5.sp, color = PwTextSub, modifier = Modifier.padding(top = 6.dp))
                        Row(Modifier.padding(top = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                            listOf(pad(cdD) to s.daysLabel, pad(cdH) to s.hrsLabel, pad(cdM) to s.minLabel, pad(cdS) to s.secLabel)
                                .forEachIndexed { i, (v, label) ->
                                    CountdownCell(v, label, if (i == 3) accent else PwText, Modifier.weight(1f))
                                }
                        }
                        Row(Modifier.fillMaxWidth().padding(top = 14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(s.lightsOut, fontSize = 12.sp, color = PwTextMuted)
                            Text("SUN 05 JUL · 15:00", fontFamily = FontFamily.Monospace, fontSize = 12.sp, color = Color(0xFFE8E8EC))
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            SectionHeader(s.titleFight, s.standingsLink, accent)
            Box(modifier = Modifier.padding(horizontal = 14.dp).clip(RoundedCornerShape(16.dp)).background(PwCard).border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(16.dp))) {
                Column {
                    top3.forEachIndexed { i, d ->
                        val teamColor = teamMap[d.teamId]?.color ?: accent
                        Column {
                            if (i > 0) Divider(color = Color.White.copy(alpha = 0.05f), thickness = 1.dp)
                            Row(Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 13.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text("${i + 1}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = PwTextMuted, modifier = Modifier.width(18.dp))
                                Box(Modifier.width(4.dp).height(30.dp).clip(RoundedCornerShape(2.dp)).background(teamColor))
                                Column(Modifier.weight(1f)) {
                                    Text(d.dName(isKorean), fontWeight = FontWeight.Bold, fontSize = 14.5.sp, color = PwText)
                                    Text(teamMap[d.teamId]?.tName(isKorean) ?: "", fontSize = 11.sp, color = PwTextMuted)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("${d.pts}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = PwText)
                                    Text(s.ptsLabel, fontSize = 9.sp, color = PwTextDim, letterSpacing = 0.5.sp)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            Text(s.myTeamLabel, modifier = Modifier.padding(start = 18.dp, bottom = 10.dp), fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, color = PwText)
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(PwCard)
                    .border(1.dp, accent.copy(alpha = 0.22f), RoundedCornerShape(18.dp))
            ) {
                Column {
                    // ── Colored header ───────────────────────────────────────
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.horizontalGradient(listOf(accent.copy(alpha = 0.28f), Color.Transparent)))
                    ) {
                        // Left accent stripe
                        Box(Modifier.width(4.dp).matchParentSize().background(accent))
                        // Watermark team code
                        Row(
                            Modifier.matchParentSize().padding(end = 14.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                selectedTeam.short,
                                fontSize = 62.sp,
                                fontWeight = FontWeight.Black,
                                color = accent.copy(alpha = 0.10f),
                                letterSpacing = (-2).sp,
                            )
                        }
                        // Team info
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 18.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(selectedTeam.tName(isKorean), fontWeight = FontWeight.Black, fontSize = 23.sp, color = PwText, letterSpacing = (-0.5).sp)
                                Text(selectedTeam.tFull(isKorean), fontSize = 11.sp, color = PwTextSub, modifier = Modifier.padding(top = 3.dp))
                                Row(Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                                    Column {
                                        Text("P$constrPos", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 19.sp, color = accent)
                                        Text(s.constructorsLabel, fontSize = 8.sp, color = PwTextDim, letterSpacing = 0.5.sp)
                                    }
                                    Column {
                                        Text("${selectedTeam.pts}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 19.sp, color = PwText)
                                        Text(s.ptsLabel, fontSize = 8.sp, color = PwTextDim, letterSpacing = 0.5.sp)
                                    }
                                }
                            }
                        }
                    }

                    Divider(color = Color.White.copy(alpha = 0.05f))

                    // ── Drivers ──────────────────────────────────────────────
                    Row(Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                        myDrivers.take(2).forEach { d ->
                            Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.04f)).padding(11.dp)) {
                                Text("#${d.number}", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = accent)
                                Text(d.dLast(isKorean), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PwText, modifier = Modifier.padding(top = 4.dp))
                                Text("P${allDrivers.indexOfFirst { it.id == d.id } + 1} · ${d.pts} ${s.ptsLabel}", fontSize = 11.sp, color = PwTextSub, modifier = Modifier.padding(top = 1.dp))
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            SectionHeader(s.latestLabel, s.newsLink, accent)
            Column(Modifier.padding(horizontal = 14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                newsFeed.take(2).forEach { n ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PwCard)
                            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(14.dp)).padding(11.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(modifier = Modifier.size(54.dp).clip(RoundedCornerShape(9.dp))) {
                            Box(Modifier.fillMaxSize().background(Brush.linearGradient(135f, listOf(Color(0xFF22222A), Color(0xFF191920)))))
                            if (n.imageRes != 0) Image(painter = painterResource(n.imageRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                        Column(Modifier.weight(1f)) {
                            Text(if (isKorean) n.categoryKo else n.category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.sp)
                            Text(if (isKorean) n.titleKo else n.title, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp, lineHeight = 16.sp, color = PwText, modifier = Modifier.padding(top = 3.dp))
                            Text("${n.time} · ${n.readTime}", fontFamily = FontFamily.Monospace, fontSize = 10.5.sp, color = PwTextDim, modifier = Modifier.padding(top = 5.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PulseDot(color: Color) {
    val inf = rememberInfiniteTransition(label = "pulse")
    val alpha by inf.animateFloat(initialValue = 1f, targetValue = 0.35f, animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse), label = "alpha")
    Box(Modifier.size(7.dp).background(color.copy(alpha = alpha), CircleShape))
}

@Composable
private fun CountdownCell(value: String, label: String, valueColor: Color, modifier: Modifier) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.045f)).padding(vertical = 11.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 25.sp, letterSpacing = (-1).sp, color = valueColor)
        Text(label, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = PwTextDim, letterSpacing = 1.sp, modifier = Modifier.padding(top = 3.dp))
    }
}

@Composable
private fun SectionHeader(title: String, action: String, accent: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp).padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, color = PwText, letterSpacing = 0.2.sp)
        Text(action, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = accent)
    }
}

private fun Brush.Companion.linearGradient(angle: Float, colors: List<Color>): Brush {
    val rad = Math.toRadians(angle.toDouble())
    val cos = Math.cos(rad).toFloat(); val sin = Math.sin(rad).toFloat()
    return linearGradient(colors = colors, start = androidx.compose.ui.geometry.Offset(0f + (1 - cos) * 500, 0f + (1 - sin) * 500), end = androidx.compose.ui.geometry.Offset(cos * 1000, sin * 1000))
}
