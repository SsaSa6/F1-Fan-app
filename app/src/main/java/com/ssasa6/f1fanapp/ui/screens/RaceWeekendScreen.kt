package com.ssasa6.f1fanapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
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
fun RaceWeekendScreen(
    race: Race,
    accent: Color,
    isKorean: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler { onBack() }

    val sessions = race.weekendSchedule()
    val days = sessions.map { it.day }.distinct()
    val isDone = race.status == "done"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PwBg)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.statusBarsPadding())

        // ── Top bar ──────────────────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onBack() }
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Icon(Icons.Default.ArrowBack, null, tint = accent, modifier = Modifier.size(15.dp))
                Text(if (isKorean) "레이스" else "Races", color = accent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White.copy(alpha = 0.07f))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(race.code, color = PwTextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }
        }

        // ── Hero card ────────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(PwCard)
                .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(20.dp))
        ) {
            // Left accent stripe
            Box(Modifier.width(4.dp).matchParentSize().background(accent))

            Column(Modifier.padding(start = 20.dp, top = 22.dp, bottom = 22.dp, end = 16.dp)) {
                Text(
                    "ROUND ${race.round}",
                    color = accent, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.5.sp,
                )
                Spacer(Modifier.height(10.dp))
                if (isKorean) {
                    Text(
                        race.gpKo,
                        color = PwText, fontSize = 26.sp, fontWeight = FontWeight.Black, lineHeight = 30.sp,
                    )
                } else {
                    Text(
                        race.gp.replace(" GP", "").uppercase(),
                        color = PwText, fontSize = 30.sp, fontWeight = FontWeight.Black, letterSpacing = (-0.5).sp,
                    )
                    Text(
                        "GRAND PRIX",
                        color = accent.copy(alpha = 0.65f), fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp,
                    )
                }
                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(race.circuitName(isKorean), color = PwTextSub, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Text("·", color = PwTextFaint, fontSize = 12.sp)
                    Text(race.date, color = PwTextMuted, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // ── Status chip ──────────────────────────────────────────────────────
        Row(modifier = Modifier.padding(horizontal = 14.dp)) {
            val chipColor = when (race.status) {
                "done" -> Color(0xFFC9C9CF)
                "next" -> accent
                else   -> PwTextDim
            }
            val chipBg = when (race.status) {
                "done" -> Color.White.copy(alpha = 0.06f)
                "next" -> accent.copy(alpha = 0.15f)
                else   -> Color.White.copy(alpha = 0.04f)
            }
            val chipText = when (race.status) {
                "done" -> if (isKorean) "완료" else "COMPLETE"
                "next" -> if (isKorean) "다음 레이스" else "NEXT RACE"
                else   -> if (isKorean) "예정" else "UPCOMING"
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(chipBg)
                    .border(1.dp, chipColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(chipText, color = chipColor, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
            }
        }

        Spacer(Modifier.height(10.dp))

        // ── Winner card (done races) ──────────────────────────────────────────
        if (isDone && race.winner.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(accent.copy(alpha = 0.08f))
                    .border(1.dp, accent.copy(alpha = 0.22f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(accent.copy(alpha = 0.15f))
                            .border(1.dp, accent.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Default.EmojiEvents, null, tint = accent, modifier = Modifier.size(22.dp))
                    }
                    Column {
                        Text(
                            if (isKorean) "레이스 우승자" else "RACE WINNER",
                            color = accent.copy(alpha = 0.75f), fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold, letterSpacing = 1.5.sp,
                        )
                        Spacer(Modifier.height(3.dp))
                        Text(race.winnerName(isKorean), color = PwText, fontSize = 20.sp, fontWeight = FontWeight.Black)
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        // ── Weekend Schedule card ─────────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(PwCard)
                .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(16.dp))
        ) {
            Column {
                // Card header
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        if (isKorean) "위크엔드 일정" else "WEEKEND SCHEDULE",
                        color = PwTextSub, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp,
                    )
                    Text(
                        if (isKorean) "현지 시간" else "LOCAL TIME",
                        color = PwTextFaint, fontSize = 9.sp, letterSpacing = 1.sp,
                    )
                }
                Divider(color = Color.White.copy(alpha = 0.05f))

                // Sessions grouped by day
                days.forEachIndexed { dayIdx, day ->
                    val daySessions = sessions.filter { it.day == day }

                    // Day header row
                    Row(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Box(
                            Modifier
                                .width(3.dp)
                                .height(13.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(accent)
                        )
                        Text(day, color = accent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }

                    // Session rows for this day
                    daySessions.forEach { slot ->
                        val name = if (isKorean) slot.nameKo else slot.nameEn
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (slot.isRace) accent.copy(alpha = 0.07f) else Color.Transparent)
                                .padding(
                                    start = 27.dp,
                                    end = 16.dp,
                                    top = if (slot.isRace) 13.dp else 9.dp,
                                    bottom = if (slot.isRace) 13.dp else 9.dp,
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Box(
                                    Modifier
                                        .size(if (slot.isRace) 8.dp else 5.dp)
                                        .clip(CircleShape)
                                        .background(if (slot.isRace) accent else PwTextFaint)
                                )
                                Text(
                                    name,
                                    color = if (slot.isRace) PwText else PwTextSub,
                                    fontSize = if (slot.isRace) 14.sp else 13.sp,
                                    fontWeight = if (slot.isRace) FontWeight.Bold else FontWeight.Medium,
                                )
                            }
                            Text(
                                slot.time,
                                color = if (slot.isRace) accent else PwTextMuted,
                                fontSize = if (slot.isRace) 15.sp else 13.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = if (slot.isRace) FontWeight.Bold else FontWeight.Normal,
                            )
                        }
                    }

                    if (dayIdx < days.size - 1) {
                        Spacer(Modifier.height(4.dp))
                        Divider(
                            color = Color.White.copy(alpha = 0.04f),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    } else {
                        Spacer(Modifier.height(14.dp))
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Spacer(Modifier.navigationBarsPadding())
    }
}
