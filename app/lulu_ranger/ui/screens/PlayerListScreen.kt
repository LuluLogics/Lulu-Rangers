package com.lulu_ranger.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lulu_ranger.R
import com.lulu_ranger.ui.components.BottomNavigationBar
import com.lulu_ranger.ui.components.PlayerCard
import com.lulu_ranger.ui.components.PlayerDetail
import com.lulu_ranger.viewmodel.FavouriteViewModel
import com.lulu_ranger.viewmodel.PlayerViewModel
import com.lulu_ranger.data.model.Player
import kotlinx.coroutines.delay

@Composable
fun PlayerListScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel = viewModel(),
    favouriteViewModel: FavouriteViewModel
) {
    val players = playerViewModel.players
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    var randomPlayer by remember { mutableStateOf<Player?>(null) }

    val groupedPlayers = mapOf(
        "Defenders" to players.filter { it.position == "Defender" },
        "Midfielders" to players.filter { it.position == "Midfielder" },
        "Wingers" to players.filter { it.position == "Winger" },
        "Forwards" to players.filter { it.position == "Forward" }
    )

    val hallOfFamePlayers = listOf(
        Pair("Dele Ali", R.drawable.dele),
        Pair("Zlatan", R.drawable.zlatan),
        Pair("Thiery Henry", R.drawable.henry),
        Pair("Neymar Jr", R.drawable.neymar),
        Pair("Johan Cruyff", R.drawable.johan)
    )

    var currentHofPlayerIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentHofPlayerIndex = (currentHofPlayerIndex + 1) % hallOfFamePlayers.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.stars),
            contentDescription = "Stars Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        SpinningCrestLogo()
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color(0xFF19376D))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Lulu Rangers FC",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Established in 1950, Lulu Rangers FC is one of the most successful teams in the history of football, winning 32 Bundesliga titles and 6 UEFA Champions League trophies. With a legacy of excellence, Lulu Rangers is known for its skilled players and passionate fanbase.",
                            fontSize = 16.sp,
                            color = Color.White,
                            lineHeight = 22.sp
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                randomPlayer = players.random()
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF19376D)),
                            shape = CircleShape

                        ) {
                            Text(
                                text = "* Random Selector *",
                                fontSize = 18.sp,
                                color = Color.Yellow,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }

                groupedPlayers.forEach { (position, playersInPosition) ->
                    item {
                        Text(
                            text = position,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .background(Color(0xFF19376D))
                                .fillMaxWidth()
                                .padding(8.dp),
                            color = Color.White
                        )
                    }

                    items(playersInPosition) { player ->
                        PlayerCard(
                            player = player,
                            isFavorite = favouriteViewModel.favouritePlayers.contains(player),
                            onFavoriteToggle = { favouriteViewModel.toggleFavourite(player) },
                            onClick = {
                                selectedPlayer = player
                                showDialog = true
                            }
                        )
                    }
                }

                item {
                    HallOfFameSection(hallOfFamePlayers[currentHofPlayerIndex])
                }
            }

            randomPlayer?.let { player ->
                val playerBackgroundRes = when (player.name) {
                    "Carles Puyol" -> R.drawable.carles_run
                    "Virgil van Dijk" -> R.drawable.vvd_run
                    "Antonio Rudiger" -> R.drawable.rudiger_run
                    "Alessandro Nesta" -> R.drawable.nesta_run
                    "Leroy Sané" -> R.drawable.sane_run
                    "Zinedine Zidane" -> R.drawable.zidane_run
                    "Doctor Khumalo" -> R.drawable.dr_run
                    "Lionel Messi" -> R.drawable.messi_run
                    "Pelé" -> R.drawable.pele_run
                    "Diego Maradona" -> R.drawable.maradona_run
                    else -> R.drawable.stars
                }

                AlertDialog(
                    onDismissRequest = { randomPlayer = null },
                    confirmButton = {
                        TextButton(onClick = { randomPlayer = null }) {
                            Text("Close",  color = Color(0xFF19376D))
                        }
                    },
                    title = {
                        Text(
                            text = player.name,
                            color = Color(0xFF19376D),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF0B2447))
                        ) {
                            Image(
                                painter = painterResource(id = playerBackgroundRes),
                                contentDescription = "${player.name} Background",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    colors = CardDefaults.cardColors(containerColor = Color(0x661C1C1C))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Personal",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Position: ${player.position}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Age: ${player.age}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Nationality: ${player.nationality}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Height: ${player.height}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Weight: ${player.weight}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    }
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    colors = CardDefaults.cardColors(containerColor = Color(0x661C1C1C))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Statistics",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Goals: ${player.goals}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Assists: ${player.assists}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Appearances: ${player.appearances}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Yellow Cards: ${player.yellowCards}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Red Cards: ${player.redCards}",
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }

            if (showDialog && selectedPlayer != null) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Close")
                        }
                    },
                    text = {
                        PlayerDetail(player = selectedPlayer!!)
                    }
                )
            }
        }
    }
}

@Composable
fun SpinningCrestLogo() {
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
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Spinning Crest",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16f * density
            },
        contentScale = ContentScale.Crop
    )
}


@Composable
fun HallOfFameSection(hofPlayer: Pair<String, Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF19376D), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hall of Fame",
            fontSize = 32.sp,
            color = Color.Yellow,
            fontWeight = FontWeight.Bold,
            )
        Image(
            painter = painterResource(id = hofPlayer.second),
            contentDescription = "${hofPlayer.first} Image",
            modifier = Modifier
                .size(170.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = hofPlayer.first,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))


    }
}