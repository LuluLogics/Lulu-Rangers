package com.lulu_ranger.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lulu_ranger.R
import com.lulu_ranger.data.model.Player
import com.lulu_ranger.ui.components.BottomNavigationBar
import com.lulu_ranger.viewmodel.PlayerViewModel
import kotlin.math.roundToInt

@Composable
fun SoccerFormationScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel = viewModel()
) {
    val players = playerViewModel.players

    val initialPlayerPositions = mapOf(
        players[0] to Offset(0.5f, 0.2f),  // Goalkeeper
        players[1] to Offset(0.2f, 0.4f),  // Defender
        players[2] to Offset(0.8f, 0.4f),  // Defender
        players[3] to Offset(0.35f, 0.6f), // Midfielder
        players[4] to Offset(0.65f, 0.6f), // Midfielder
        players[5] to Offset(0.5f, 0.8f),  // Forward
    )

    var playerPositions by remember { mutableStateOf(initialPlayerPositions) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B3A57)),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pitch),
                contentDescription = "Soccer Pitch",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            players.forEach { player ->
                val position = playerPositions[player] ?: Offset(0.5f, 0.5f)
                DraggablePlayer(
                    player = player,
                    position = position,
                    onPositionChange = { newPosition ->
                        playerPositions = playerPositions.toMutableMap().apply {
                            this[player] = newPosition
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DraggablePlayer(player: Player, position: Offset, onPositionChange: (Offset) -> Unit) {
    var offsetX by remember { mutableStateOf(position.x) }
    var offsetY by remember { mutableStateOf(position.y) }

    Column(
        modifier = Modifier
            .offset { IntOffset((offsetX * 1000).roundToInt(), (offsetY * 1600).roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x / 1000f
                    offsetY += dragAmount.y / 1600f
                    onPositionChange(Offset(offsetX, offsetY))
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = player.imageRes),
            contentDescription = player.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .graphicsLayer {
                    rotationY = if (offsetX != 0f || offsetY != 0f) 360f else 0f
                },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = player.name,
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
