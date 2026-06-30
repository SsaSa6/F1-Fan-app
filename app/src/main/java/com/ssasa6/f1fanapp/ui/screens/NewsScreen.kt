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
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.ssasa6.f1fanapp.data.*
import com.ssasa6.f1fanapp.ui.appStrings
import com.ssasa6.f1fanapp.ui.theme.*

@Composable
fun NewsScreen(accent: Color, isKorean: Boolean, modifier: Modifier = Modifier) {
    val s = appStrings(isKorean)
    var activeFilter by remember { mutableStateOf(0) }
    val filters = listOf(s.filterAll, s.filterReports, s.filterTech, s.filterTeams)

    Column(modifier = modifier.fillMaxSize().background(PwBg)) {
        Column(modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 18.dp, vertical = 14.dp)) {
            Text(s.paddockLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.6.sp)
            Text(s.latestNewsTitle, fontWeight = FontWeight.Black, fontSize = 25.sp, color = PwText, letterSpacing = (-0.6).sp, modifier = Modifier.padding(top = 3.dp))
            LazyRow(contentPadding = PaddingValues(top = 14.dp), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                itemsIndexed(filters) { i, f ->
                    val active = i == activeFilter
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(9.dp))
                            .background(if (active) accent else Color.White.copy(alpha = 0.06f))
                            .clickable { activeFilter = i }.padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(f, fontWeight = if (active) FontWeight.Bold else FontWeight.SemiBold, fontSize = 12.sp, color = if (active) Color.Black else Color(0xFFC9C9CF))
                    }
                }
            }
        }

        LazyColumn(contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp)) {
            item {
                val featured = newsFeed.first()
                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(PwCard)
                        .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(18.dp))
                ) {
                    Column {
                        Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                            Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF24242C), Color(0xFF191920)))))
                            if (featured.imageRes != 0) {
                                Image(painter = painterResource(featured.imageRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                            }
                            Box(
                                modifier = Modifier.padding(14.dp).clip(RoundedCornerShape(5.dp)).background(accent).padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(if (isKorean) featured.categoryKo else featured.category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Black, letterSpacing = 1.sp)
                            }
                        }
                        Column(Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                            Text(if (isKorean) featured.titleKo else featured.title, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp, lineHeight = 21.sp, letterSpacing = (-0.2).sp, color = PwText)
                            Text("${featured.time} · ${featured.readTime} read", fontFamily = FontFamily.Monospace, fontSize = 10.5.sp, color = PwTextDim, modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
            }

            items(newsFeed.drop(1)) { n ->
                Row(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PwCard)
                        .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(14.dp)).padding(11.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.size(62.dp).clip(RoundedCornerShape(10.dp))) {
                        Box(Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF24242C), Color(0xFF191920)))))
                        if (n.imageRes != 0) {
                            Image(painter = painterResource(n.imageRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                    }
                    Column(Modifier.weight(1f)) {
                        Text(if (isKorean) n.categoryKo else n.category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = accent, letterSpacing = 1.sp)
                        Text(if (isKorean) n.titleKo else n.title, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, lineHeight = 16.sp, color = PwText, modifier = Modifier.padding(top = 4.dp))
                        Text("${n.time} · ${n.readTime}", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = PwTextDim, modifier = Modifier.padding(top = 5.dp))
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            item { Spacer(Modifier.height(6.dp)) }
        }
    }
}
