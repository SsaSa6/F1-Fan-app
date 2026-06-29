package com.ssasa6.f1fanapp.data

import androidx.compose.ui.graphics.Color
import com.ssasa6.f1fanapp.ui.theme.*

data class Team(
    val id: String,
    val name: String,
    val short: String,
    val full: String,
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
    val last: String,
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
    val circuit: String,
    val date: String,
    val status: String,
    val winner: String,
)

data class NewsItem(
    val category: String,
    val title: String,
    val time: String,
    val readTime: String,
)

val allTeams = listOf(
    Team("mercedes", "Mercedes",    "MER", "Mercedes-AMG Petronas",        TeamMercedes,  TeamMercedesSec,  302, "Brackley, UK",       "Mercedes",    "Toto Wolff",        2010, 8),
    Team("ferrari",  "Ferrari",     "FER", "Scuderia Ferrari HP",          TeamFerrari,   TeamFerrariSec,   204, "Maranello, ITA",     "Ferrari",     "Fred Vasseur",      1950, 16),
    Team("mclaren",  "McLaren",     "MCL", "McLaren Formula 1 Team",       TeamMcLaren,   TeamMcLarenSec,   156, "Woking, UK",         "Mercedes",    "Andrea Stella",     1966, 9),
    Team("redbull",  "Red Bull",    "RBR", "Oracle Red Bull Racing",       TeamRedBull,   TeamRedBullSec,   101, "Milton Keynes, UK",  "Red Bull Ford","Laurent Mekies",   2005, 6),
    Team("williams", "Williams",    "WIL", "Atlassian Williams Racing",    TeamWilliams,  TeamWilliamsSec,   59, "Grove, UK",          "Mercedes",    "James Vowles",      1978, 9),
    Team("audi",     "Audi",        "AUD", "Audi F1 Team",                 TeamAudi,      TeamAudiSec,       33, "Hinwil, SUI",        "Audi",        "Jonathan Wheatley", 2026, 0),
    Team("aston",    "Aston Martin","AMR", "Aston Martin Aramco",          TeamAston,     TeamAstonSec,      29, "Silverstone, UK",    "Honda",       "Andy Cowell",       2021, 0),
    Team("alpine",   "Alpine",      "ALP", "BWT Alpine F1 Team",           TeamAlpine,    TeamAlpineSec,     16, "Enstone, UK",        "Mercedes",    "Flavio Briatore",   1986, 2),
    Team("rb",       "Racing Bulls","RB",  "Visa Cash App Racing Bulls",   TeamRB,        TeamRBSec,         16, "Faenza, ITA",        "Red Bull Ford","Alan Permane",     2006, 0),
    Team("haas",     "Haas",        "HAA", "MoneyGram Haas F1 Team",       TeamHaas,      TeamHaasSec,       15, "Kannapolis, USA",    "Ferrari",     "Ayao Komatsu",      2016, 0),
    Team("cadillac", "Cadillac",    "CAD", "Cadillac F1 Team",             TeamCadillac,  TeamCadillacSec,    3, "Indianapolis, USA",  "Ferrari",     "Graeme Lowdon",     2026, 0),
)

val teamMap: Map<String, Team> = allTeams.associateBy { it.id }

val allDrivers = listOf(
    Driver("ant", "Kimi",     "Antonelli", "ANT", 12, "ITA", "mercedes", 171, 5, 8, 3),
    Driver("rus", "George",   "Russell",   "RUS", 63, "GBR", "mercedes", 131, 2, 6, 2),
    Driver("ham", "Lewis",    "Hamilton",  "HAM", 44, "GBR", "ferrari",  118, 1, 5, 1),
    Driver("lec", "Charles",  "Leclerc",   "LEC", 16, "MON", "ferrari",   86, 2, 4, 2),
    Driver("nor", "Lando",    "Norris",    "NOR",  4, "GBR", "mclaren",   84, 1, 3, 1),
    Driver("pia", "Oscar",    "Piastri",   "PIA", 81, "AUS", "mclaren",   72, 0, 2, 0),
    Driver("ver", "Max",      "Verstappen","VER",  1, "NED", "redbull",   71, 0, 2, 1),
    Driver("sai", "Carlos",   "Sainz",     "SAI", 55, "ESP", "williams",  33, 0, 1, 0),
    Driver("had", "Isack",    "Hadjar",    "HAD",  6, "FRA", "redbull",   30, 0, 0, 0),
    Driver("alb", "Alex",     "Albon",     "ALB", 23, "THA", "williams",  26, 0, 0, 0),
    Driver("alo", "Fernando", "Alonso",    "ALO", 14, "ESP", "aston",     22, 0, 1, 0),
    Driver("bor", "Gabriel",  "Bortoleto", "BOR",  5, "BRA", "audi",      18, 0, 0, 0),
    Driver("hul", "Nico",     "Hülkenberg","HUL", 27, "GER", "audi",      15, 0, 0, 0),
    Driver("gas", "Pierre",   "Gasly",     "GAS", 10, "FRA", "alpine",    13, 0, 0, 0),
    Driver("law", "Liam",     "Lawson",    "LAW", 30, "NZL", "rb",        11, 0, 0, 0),
    Driver("bea", "Oliver",   "Bearman",   "BEA", 87, "GBR", "haas",       9, 0, 0, 0),
    Driver("str", "Lance",    "Stroll",    "STR", 18, "CAN", "aston",      7, 0, 0, 0),
    Driver("oco", "Esteban",  "Ocon",      "OCO", 31, "FRA", "haas",       6, 0, 0, 0),
    Driver("lin", "Arvid",    "Lindblad",  "LIN", 37, "GBR", "rb",         5, 0, 0, 0),
    Driver("col", "Franco",   "Colapinto", "COL", 43, "ARG", "alpine",     3, 0, 0, 0),
    Driver("per", "Sergio",   "Pérez",     "PER", 11, "MEX", "cadillac",   2, 0, 0, 0),
    Driver("bot", "Valtteri", "Bottas",    "BOT", 77, "FIN", "cadillac",   1, 0, 0, 0),
)

val raceCalendar = listOf(
    Race("01","AUS","Australian GP",    "Albert Park",       "06–08 MAR","done","Russell"),
    Race("02","CHN","Chinese GP",       "Shanghai Intl",     "20–22 MAR","done","Antonelli"),
    Race("03","JPN","Japanese GP",      "Suzuka",            "03–05 APR","done","Antonelli"),
    Race("04","BHR","Bahrain GP",       "Sakhir",            "10–12 APR","done","Hamilton"),
    Race("05","SAU","Saudi Arabian GP", "Jeddah",            "17–19 APR","done","Leclerc"),
    Race("06","MIA","Miami GP",         "Miami Intl",        "01–03 MAY","done","Antonelli"),
    Race("07","CAN","Canadian GP",      "Montréal",          "22–24 MAY","done","Antonelli"),
    Race("08","MON","Monaco GP",        "Monte Carlo",       "05–07 JUN","done","Leclerc"),
    Race("09","ESP","Spanish GP",       "Barcelona",         "12–14 JUN","done","Antonelli"),
    Race("10","AUT","Austrian GP",      "Red Bull Ring",     "26–28 JUN","done","Russell"),
    Race("11","GBR","British GP",       "Silverstone",       "03–05 JUL","next",""),
    Race("12","BEL","Belgian GP",       "Spa-Francorchamps", "17–19 JUL","soon",""),
    Race("13","HUN","Hungarian GP",     "Hungaroring",       "24–26 JUL","soon",""),
    Race("14","NED","Dutch GP",         "Zandvoort",         "21–23 AUG","soon",""),
    Race("15","ITA","Italian GP",       "Monza",             "04–06 SEP","soon",""),
    Race("16","ESP","Madrid GP",        "Madring",           "11–13 SEP","soon",""),
    Race("17","AZE","Azerbaijan GP",    "Baku",              "25–27 SEP","soon",""),
    Race("18","SGP","Singapore GP",     "Marina Bay",        "09–11 OCT","soon",""),
    Race("19","USA","United States GP", "COTA",              "23–25 OCT","soon",""),
    Race("20","MXC","Mexico City GP",   "Hnos Rodríguez",   "30 OCT–01 NOV","soon",""),
    Race("21","BRA","São Paulo GP",     "Interlagos",        "06–08 NOV","soon",""),
    Race("22","LVG","Las Vegas GP",     "Las Vegas Strip",   "19–21 NOV","soon",""),
    Race("23","QAT","Qatar GP",         "Lusail",            "27–29 NOV","soon",""),
    Race("24","ABU","Abu Dhabi GP",     "Yas Marina",        "04–06 DEC","soon",""),
)

val newsFeed = listOf(
    NewsItem("RACE REPORT","Russell storms to Austria win as Antonelli's title lead is trimmed to 40","2h ago","3 min"),
    NewsItem("FERRARI","Hamilton hails latest upgrade as the 'turning point' in his title charge","5h ago","4 min"),
    NewsItem("PREVIEW","Silverstone: can Mercedes make it three wins on the bounce?","8h ago","5 min"),
    NewsItem("AUDI","Bortoleto stuns the paddock with a maiden Q3 appearance in Spielberg","11h ago","2 min"),
    NewsItem("TECH","Active aero, explained: how the 2026 cars rewrite the overtake","1d ago","7 min"),
    NewsItem("CADILLAC","Pérez eyes first points as the American rookies find their feet","1d ago","3 min"),
)

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
    Triple("FRI · 03 JUL", "Practice 1",  "12:30"),
    Triple("FRI · 03 JUL", "Practice 2",  "16:00"),
    Triple("SAT · 04 JUL", "Practice 3",  "11:30"),
    Triple("SAT · 04 JUL", "Qualifying",  "15:00"),
    Triple("SUN · 05 JUL", "Grand Prix",  "15:00"),
)
