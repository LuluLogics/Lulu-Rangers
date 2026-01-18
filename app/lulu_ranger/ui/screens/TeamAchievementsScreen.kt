package com.lulu_ranger.ui.screens

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lulu_ranger.R
import com.lulu_ranger.ui.components.BottomNavigationBar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay

@Composable
fun TeamAchievementsScreen(navController: NavHostController) {
    val achievements = listOf(
        Achievement("UEFA Champions League Winners", 6, R.drawable.champions),
        Achievement("FIFA Club World Cup Winners", 3, R.drawable.world_cup),
        Achievement("Premier League Champions", 5, R.drawable.premier),
        Achievement("Bundesliga Champions", 32, R.drawable.bundas),
        Achievement("Copa del Rey Winners", 20, R.drawable.copa),
        Achievement("FA Cup Winners", 8, R.drawable.fa),
        Achievement("UEFA Europa League Winners", 3, R.drawable.euro)
    )

    val mediaPlayer = remember { MediaPlayer.create(navController.context, R.raw.champions_theme) }
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.stars),
            contentDescription = "Stars Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(if (isPlaying) Color.Green else Color.Red)
                .clickable {
                    if (isPlaying) {
                        mediaPlayer.pause()
                        isPlaying = false
                    } else {
                        mediaPlayer.start()
                        isPlaying = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier.size(24.dp)
            )
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        SpinningCrownLogo()
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF283593), Color(0xFF1A237E))
                                )
                            )
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.fa),
                            contentDescription = "Team Trophy Icon",
                            modifier = Modifier.size(48.dp),
                            tint = Color.Yellow
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Trophy Room",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Surface(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                            color = Color.Transparent
                        ) {
                            Text(
                                text = "Founded in 1950, Lulu Rangers FC has a long and storied history, with 32 Bundesliga titles and 6 UEFA Champions League trophies to their name. Known for their passion and dedication, the Rangers have consistently been at the top of world football.",
                                fontSize = 18.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 24.sp,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {  },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clip(CircleShape), // Rounded button
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                        ) {
                            Text(
                                text = "Explore Achievements",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A237E) // Dark blue text color
                            )
                        }
                    }

                }



                achievements.forEach { achievement ->
                    item {
                        AchievementCard(achievement)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "LuluLogics Arena",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    StadiumImageSlider()
                }
            }
        }
    }
}

@Composable
fun StadiumImageSlider() {
    val stadiumImages = listOf(
        R.drawable.in_statium,
        R.drawable.in_stadium2,
        R.drawable.stadium
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentImageIndex = (currentImageIndex + 1) % stadiumImages.size
        }
    }

    Image(
        painter = painterResource(id = stadiumImages[currentImageIndex]),
        contentDescription = "Stadium Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SpinningCrownLogo() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.crown),
        contentDescription = "Spinning Crown",
        modifier = Modifier
            .size(180.dp)
            .clip(CircleShape)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16f * density
            },
        contentScale = ContentScale.Crop
    )
}


data class Achievement(val title: String, val count: Int, val trophyResId: Int)

@Composable
fun AchievementCard(achievement: Achievement) {
    var showDetailsDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDetailsDialog = true },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF19376D)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = achievement.trophyResId),
                contentDescription = "Trophy",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = achievement.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${achievement.count} Times",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        AchievementProgressBar(
            title = achievement.title,
            currentCount = achievement.count,
            goal = getGoalForAchievement(achievement.title)
        )
    }

    if (showDetailsDialog) {
        AchievementDetailDialog(achievement) {
            showDetailsDialog = false
        }
    }
}

@Composable
fun AchievementDetailDialog(achievement: Achievement, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = achievement.title, fontWeight = FontWeight.Bold)
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = achievement.trophyResId),
                    contentDescription = "Trophy Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = generateAchievementDetails(achievement.title),
                    color = Color.Yellow,
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

fun getGoalForAchievement(title: String): Int {
    return when (title) {
        "UEFA Champions League Winners" -> 10
        "FIFA Club World Cup Winners" -> 5
        "Premier League Champions" -> 10
        "Bundesliga Champions" -> 40
        "Copa del Rey Winners" -> 25
        "FA Cup Winners" -> 15
        "UEFA Europa League Winners" -> 6
        else -> 10
    }
}

@Composable
fun AchievementProgressBar(title: String, currentCount: Int, goal: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$title: $currentCount/$goal",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        LinearProgressIndicator(
            progress = currentCount / goal.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp)),
            color = Color.Yellow
        )
    }
}



fun generateAchievementDetails(title: String): String {
    return when (title) {
        "UEFA Champions League Winners" -> {
            "Lulu Rangers won the UEFA Champions League 6 times:\n" +
                    "1. 1992\n2. 1998\n3. 2005\n4. 2011\n5. 2016\n6. 2022"
        }
        "FIFA Club World Cup Winners" -> {
            "Lulu Rangers won the FIFA Club World Cup 3 times:\n" +
                    "1. 2010\n2. 2013\n3. 2017"
        }
        "Premier League Champions" -> {
            "Lulu Rangers won the Premier League 5 times:\n" +
                    "1. 2000\n2. 2003\n3. 2009\n4. 2014\n5. 2021"
        }
        "Bundesliga Champions" -> {
            "Lulu Rangers won the Bundesliga 32 times:\n" +
                    "1. 1969\n2. 1972\n3. 1974\n4. 1980\n5. 1981\n6. 1985\n" +
                    "7. 1990\n8. 1994\n9. 1996\n10. 1999\n11. 2001\n12. 2004\n" +
                    "13. 2006\n14. 2008\n15. 2011\n16. 2012\n17. 2013\n18. 2014\n" +
                    "19. 2015\n20. 2016\n21. 2017\n22. 2018\n23. 2019\n24. 2020\n" +
                    "25. 2021\n26. 2022\n27. 2023\n28. 2024\n29. 2025\n30. 2026\n" +
                    "31. 2027\n32. 2028"
        }
        "Copa del Rey Winners" -> {
            "Lulu Rangers won the Copa del Rey 20 times:\n" +
                    "1. 1978\n2. 1981\n3. 1985\n4. 1988\n5. 1991\n6. 1994\n" +
                    "7. 1997\n8. 1999\n9. 2001\n10. 2003\n11. 2006\n12. 2008\n" +
                    "13. 2010\n14. 2013\n15. 2015\n16. 2017\n17. 2019\n18. 2021\n" +
                    "19. 2023\n20. 2024"
        }
        "FA Cup Winners" -> {
            "Lulu Rangers won the FA Cup 8 times:\n" +
                    "1. 1974\n2. 1979\n3. 1984\n4. 1990\n5. 1996\n6. 2004\n" +
                    "7. 2009\n8. 2018"
        }
        "UEFA Europa League Winners" -> {
            "Lulu Rangers won the UEFA Europa League 3 times:\n" +
                    "1. 2001\n2. 2009\n3. 2020"
        }
        else -> "Details not available for this achievement."
    }
}

