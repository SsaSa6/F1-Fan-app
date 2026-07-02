package com.ssasa6.f1fanapp.data

import androidx.compose.ui.graphics.Color
import com.ssasa6.f1fanapp.R
import com.ssasa6.f1fanapp.ui.theme.*

data class Team(
    val id: String,
    val name: String,
    val nameKo: String,
    val short: String,
    val full: String,
    val fullKo: String,
    val color: Color,
    val color2: Color,
    val pts: Int,
    val base: String,
    val pu: String,
    val tp: String,
    val since: Int,
    val titles: Int,
)

data class Driver(
    val id: String,
    val first: String,
    val firstKo: String,
    val last: String,
    val lastKo: String,
    val code: String,
    val number: Int,
    val nat: String,
    val teamId: String,
    val pts: Int,
    val wins: Int,
    val podiums: Int,
    val poles: Int,
)

data class Race(
    val round: String,
    val code: String,
    val gp: String,
    val gpKo: String,
    val circuit: String,
    val circuitKo: String,
    val date: String,
    val status: String,
    val winner: String,
    val winnerKo: String,
)

data class NewsItem(
    val category: String,
    val categoryKo: String,
    val title: String,
    val titleKo: String,
    val time: String,
    val readTime: String,
    val imageRes: Int = 0,
)

// ── Localization helpers ──────────────────────────────────────────────────────
fun Driver.dName(isKorean: Boolean) = if (isKorean) "$firstKo $lastKo" else "$first $last"
fun Driver.dLast(isKorean: Boolean) = if (isKorean) lastKo else last
fun Team.tName(isKorean: Boolean)   = if (isKorean) nameKo else name
fun Team.tFull(isKorean: Boolean)   = if (isKorean) fullKo else full
fun Race.gpName(isKorean: Boolean)  = if (isKorean) gpKo else gp
fun Race.circuitName(isKorean: Boolean) = if (isKorean) circuitKo else circuit
fun Race.winnerName(isKorean: Boolean)  = if (isKorean && winnerKo.isNotEmpty()) winnerKo else winner

// ── Teams ─────────────────────────────────────────────────────────────────────
val allTeams = listOf(
    Team("mercedes","Mercedes","메르세데스","MER","Mercedes-AMG Petronas","메르세데스-AMG 페트로나스",TeamMercedes,TeamMercedesSec,302,"Brackley, UK","Mercedes","Toto Wolff",2010,8),
    Team("ferrari","Ferrari","페라리","FER","Scuderia Ferrari HP","스쿠데리아 페라리 HP",TeamFerrari,TeamFerrariSec,204,"Maranello, ITA","Ferrari","Fred Vasseur",1950,16),
    Team("mclaren","McLaren","맥라렌","MCL","McLaren Formula 1 Team","맥라렌 F1 팀",TeamMcLaren,TeamMcLarenSec,156,"Woking, UK","Mercedes","Andrea Stella",1966,9),
    Team("redbull","Red Bull","레드불","RBR","Oracle Red Bull Racing","오라클 레드불 레이싱",TeamRedBull,TeamRedBullSec,101,"Milton Keynes, UK","Red Bull Ford","Laurent Mekies",2005,6),
    Team("williams","Williams","윌리엄스","WIL","Atlassian Williams Racing","애틀라시안 윌리엄스 레이싱",TeamWilliams,TeamWilliamsSec,59,"Grove, UK","Mercedes","James Vowles",1978,9),
    Team("audi","Audi","아우디","AUD","Audi F1 Team","아우디 F1 팀",TeamAudi,TeamAudiSec,33,"Hinwil, SUI","Audi","Jonathan Wheatley",2026,0),
    Team("aston","Aston Martin","애스턴 마틴","AMR","Aston Martin Aramco","애스턴 마틴 아람코",TeamAston,TeamAstonSec,29,"Silverstone, UK","Honda","Andy Cowell",2021,0),
    Team("alpine","Alpine","알핀","ALP","BWT Alpine F1 Team","BWT 알핀 F1 팀",TeamAlpine,TeamAlpineSec,16,"Enstone, UK","Mercedes","Flavio Briatore",1986,2),
    Team("rb","Racing Bulls","레이싱 불스","RB","Visa Cash App Racing Bulls","비자 캐시앱 레이싱 불스",TeamRB,TeamRBSec,16,"Faenza, ITA","Red Bull Ford","Alan Permane",2006,0),
    Team("haas","Haas","하스","HAA","MoneyGram Haas F1 Team","머니그램 하스 F1 팀",TeamHaas,TeamHaasSec,15,"Kannapolis, USA","Ferrari","Ayao Komatsu",2016,0),
    Team("cadillac","Cadillac","캐딜락","CAD","Cadillac F1 Team","캐딜락 F1 팀",TeamCadillac,TeamCadillacSec,3,"Indianapolis, USA","Ferrari","Graeme Lowdon",2026,0),
)

val teamMap: Map<String, Team> = allTeams.associateBy { it.id }

// ── Drivers ───────────────────────────────────────────────────────────────────
val allDrivers = listOf(
    Driver("ant","Kimi","키미","Antonelli","안토넬리","ANT",12,"ITA","mercedes",171,5,8,3),
    Driver("rus","George","조지","Russell","러셀","RUS",63,"GBR","mercedes",131,2,6,2),
    Driver("ham","Lewis","루이스","Hamilton","해밀턴","HAM",44,"GBR","ferrari",118,1,5,1),
    Driver("lec","Charles","샤를","Leclerc","르클레르","LEC",16,"MON","ferrari",86,2,4,2),
    Driver("nor","Lando","랜도","Norris","노리스","NOR",4,"GBR","mclaren",84,1,3,1),
    Driver("pia","Oscar","오스카","Piastri","피아스트리","PIA",81,"AUS","mclaren",72,0,2,0),
    Driver("ver","Max","막스","Verstappen","베르스타펜","VER",1,"NED","redbull",71,0,2,1),
    Driver("sai","Carlos","카를로스","Sainz","사인스","SAI",55,"ESP","williams",33,0,1,0),
    Driver("had","Isack","아이작","Hadjar","하자","HAD",6,"FRA","redbull",30,0,0,0),
    Driver("alb","Alex","알렉스","Albon","알본","ALB",23,"THA","williams",26,0,0,0),
    Driver("alo","Fernando","페르난도","Alonso","알론소","ALO",14,"ESP","aston",22,0,1,0),
    Driver("bor","Gabriel","가브리엘","Bortoleto","보톨레토","BOR",5,"BRA","audi",18,0,0,0),
    Driver("hul","Nico","니코","Hülkenberg","휠켄베르크","HUL",27,"GER","audi",15,0,0,0),
    Driver("gas","Pierre","피에르","Gasly","가슬리","GAS",10,"FRA","alpine",13,0,0,0),
    Driver("law","Liam","리암","Lawson","로슨","LAW",30,"NZL","rb",11,0,0,0),
    Driver("bea","Oliver","올리버","Bearman","베어만","BEA",87,"GBR","haas",9,0,0,0),
    Driver("str","Lance","랜스","Stroll","스트롤","STR",18,"CAN","aston",7,0,0,0),
    Driver("oco","Esteban","에스테반","Ocon","오콘","OCO",31,"FRA","haas",6,0,0,0),
    Driver("lin","Arvid","아비드","Lindblad","린드블라드","LIN",37,"GBR","rb",5,0,0,0),
    Driver("col","Franco","프랑코","Colapinto","콜라핀토","COL",43,"ARG","alpine",3,0,0,0),
    Driver("per","Sergio","세르히오","Pérez","페레스","PER",11,"MEX","cadillac",2,0,0,0),
    Driver("bot","Valtteri","발테리","Bottas","보타스","BOT",77,"FIN","cadillac",1,0,0,0),
)

// ── Race calendar ─────────────────────────────────────────────────────────────
val raceCalendar = listOf(
    Race("01","AUS","Australian GP","오스트레일리아 GP","Albert Park","앨버트 파크","06–08 MAR","done","Russell","러셀"),
    Race("02","CHN","Chinese GP","중국 GP","Shanghai Intl","상하이 인터내셔널","20–22 MAR","done","Antonelli","안토넬리"),
    Race("03","JPN","Japanese GP","일본 GP","Suzuka","스즈카","03–05 APR","done","Antonelli","안토넬리"),
    Race("04","BHR","Bahrain GP","바레인 GP","Sakhir","사키르","10–12 APR","done","Hamilton","해밀턴"),
    Race("05","SAU","Saudi Arabian GP","사우디아라비아 GP","Jeddah","제다","17–19 APR","done","Leclerc","르클레르"),
    Race("06","MIA","Miami GP","마이애미 GP","Miami Intl","마이애미 인터내셔널","01–03 MAY","done","Antonelli","안토넬리"),
    Race("07","CAN","Canadian GP","캐나다 GP","Montréal","몬트리올","22–24 MAY","done","Antonelli","안토넬리"),
    Race("08","MON","Monaco GP","모나코 GP","Monte Carlo","몬테카를로","05–07 JUN","done","Leclerc","르클레르"),
    Race("09","ESP","Spanish GP","스페인 GP","Barcelona","바르셀로나","12–14 JUN","done","Antonelli","안토넬리"),
    Race("10","AUT","Austrian GP","오스트리아 GP","Red Bull Ring","레드불 링","26–28 JUN","done","Russell","러셀"),
    Race("11","GBR","British GP","영국 GP","Silverstone","실버스톤","03–05 JUL","next","",""),
    Race("12","BEL","Belgian GP","벨기에 GP","Spa-Francorchamps","스파-프랑코르샹","17–19 JUL","soon","",""),
    Race("13","HUN","Hungarian GP","헝가리 GP","Hungaroring","헝가로링","24–26 JUL","soon","",""),
    Race("14","NED","Dutch GP","네덜란드 GP","Zandvoort","잔트보르트","21–23 AUG","soon","",""),
    Race("15","ITA","Italian GP","이탈리아 GP","Monza","몬차","04–06 SEP","soon","",""),
    Race("16","ESP","Madrid GP","마드리드 GP","Madring","마드링","11–13 SEP","soon","",""),
    Race("17","AZE","Azerbaijan GP","아제르바이잔 GP","Baku","바쿠","25–27 SEP","soon","",""),
    Race("18","SGP","Singapore GP","싱가포르 GP","Marina Bay","마리나 베이","09–11 OCT","soon","",""),
    Race("19","USA","United States GP","미국 GP","COTA","COTA","23–25 OCT","soon","",""),
    Race("20","MXC","Mexico City GP","멕시코시티 GP","Hnos Rodríguez","에르마노스 로드리게스","30 OCT–01 NOV","soon","",""),
    Race("21","BRA","São Paulo GP","상파울루 GP","Interlagos","인테를라고스","06–08 NOV","soon","",""),
    Race("22","LVG","Las Vegas GP","라스베이거스 GP","Las Vegas Strip","라스베이거스 스트립","19–21 NOV","soon","",""),
    Race("23","QAT","Qatar GP","카타르 GP","Lusail","루사일","27–29 NOV","soon","",""),
    Race("24","ABU","Abu Dhabi GP","아부다비 GP","Yas Marina","야스 마리나","04–06 DEC","soon","",""),
)

// ── News ──────────────────────────────────────────────────────────────────────
val newsFeed = listOf(
    NewsItem("RACE REPORT","레이스 리포트","Russell storms to Austria win as Antonelli's title lead is trimmed to 40","러셀, 오스트리아 우승 질주···안토넬리 타이틀 리드 40점으로 줄어","2h ago","3 min",R.drawable.news_0),
    NewsItem("FERRARI","페라리","Hamilton hails latest upgrade as the 'turning point' in his title charge","해밀턴, 최신 업그레이드를 타이틀 도전의 '전환점'으로 극찬","5h ago","4 min",R.drawable.news_1),
    NewsItem("PREVIEW","프리뷰","Silverstone: can Mercedes make it three wins on the bounce?","실버스톤: 메르세데스, 3연승 달성할 수 있을까?","8h ago","5 min",R.drawable.news_2),
    NewsItem("AUDI","아우디","Bortoleto stuns the paddock with a maiden Q3 appearance in Spielberg","보르톨레토, 슈필베르크 Q3 첫 진출로 패독 놀라게 해","11h ago","2 min",R.drawable.news_3),
    NewsItem("TECH","기술","Active aero, explained: how the 2026 cars rewrite the overtake","액티브 에어로 해설: 2026 머신이 추월을 바꾸는 방법","1d ago","7 min",R.drawable.news_4),
    NewsItem("CADILLAC","캐딜락","Pérez eyes first points as the American rookies find their feet","페레스, 미국 루키팀 적응하며 첫 포인트 노린다","1d ago","3 min",R.drawable.news_5),
)

// ── Other data ────────────────────────────────────────────────────────────────
val recentForm: Map<String, List<Any>> = mapOf(
    "ant" to listOf(2, 1, 1, 4, 3),
    "rus" to listOf(1, 3, 2, 5, 4),
    "ham" to listOf(3, 5, 2, 4, 5),
    "lec" to listOf(4, 2, 3, 6, 2),
    "nor" to listOf(6, 4, 7, 5, 4),
    "pia" to listOf(5, 6, 4, 7, 6),
    "ver" to listOf(6, "DNF", 5, 8, 7),
    "sai" to listOf(9, 11, 8, 10, 7),
    "had" to listOf(10, 8, 9, 11, 10),
    "alb" to listOf(8, 9, 11, 9, 8),
    "alo" to listOf(12, 9, "DNF", 11, 10),
    "bor" to listOf("DNF", 14, 12, 15, 9),
    "gas" to listOf(13, 16, 14, 12, 15),
    "law" to listOf(15, 12, 17, 14, 13),
    "bea" to listOf(11, 13, 10, "DNF", 12),
    "per" to listOf(16, 17, 15, "DNF", 14),
)

val britishGpSessions = listOf(
    Triple("FRI · 03 JUL", "Practice 1", "12:30"),
    Triple("FRI · 03 JUL", "Practice 2", "16:00"),
    Triple("SAT · 04 JUL", "Practice 3", "11:30"),
    Triple("SAT · 04 JUL", "Qualifying",  "15:00"),
    Triple("SUN · 05 JUL", "Grand Prix",  "15:00"),
)

data class SessionSlot(
    val day: String,
    val nameEn: String,
    val nameKo: String,
    val time: String,
    val isRace: Boolean = false,
)

private fun Race.weekendDays(): Triple<String, String, String> {
    if (code == "MXC") return Triple("FRI · 30 OCT", "SAT · 31 OCT", "SUN · 01 NOV")
    val parts = date.split("–")
    if (parts.size != 2) return Triple("FRI", "SAT", "SUN")
    val startDay = parts[0].trim().toIntOrNull() ?: return Triple("FRI", "SAT", "SUN")
    val endParts = parts[1].trim().split(" ")
    val endDay   = endParts[0].toIntOrNull() ?: return Triple("FRI", "SAT", "SUN")
    val month    = endParts.getOrElse(1) { "" }
    return Triple(
        "FRI · ${startDay.toString().padStart(2, '0')} $month",
        "SAT · ${(startDay + 1).toString().padStart(2, '0')} $month",
        "SUN · ${endDay.toString().padStart(2, '0')} $month",
    )
}

fun Race.weekendSchedule(): List<SessionSlot> {
    val (fri, sat, sun) = weekendDays()
    return listOf(
        SessionSlot(fri, "Practice 1", "프랙티스 1", "12:30"),
        SessionSlot(fri, "Practice 2", "프랙티스 2", "16:00"),
        SessionSlot(sat, "Practice 3", "프랙티스 3", "11:30"),
        SessionSlot(sat, "Qualifying",  "예선",       "15:00"),
        SessionSlot(sun, "Grand Prix",  "결승",       "15:00", isRace = true),
    )
}
