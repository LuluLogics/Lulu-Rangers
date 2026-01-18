package com.lulu_ranger.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lulu_ranger.data.model.Player
import com.lulu_ranger.R

@Composable
fun PlayerDetail(player: Player) {
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
                .fillMaxSize()
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
                        text = "Name: ${player.name}",
                        fontSize = 16.sp,
                        color = Color.White
                    )
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
