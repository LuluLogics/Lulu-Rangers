package com.lulu_ranger.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lulu_ranger.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.stars),
            contentDescription = "Navigation Bar Background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        NavigationBar(
            containerColor = Color.Transparent
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = "Players",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        color = Color(0xFF19376D),
                        text = "Players",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ) },
                selected = false,
                onClick = { navController.navigate("player_list") }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Favourites",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        color = Color(0xFF19376D),
                        text = "Favourites",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ) },                selected = false,
                onClick = { navController.navigate("favourites") }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Achievements",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        color = Color(0xFF19376D),
                        text = "Achievements",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ) },                selected = false,
                onClick = { navController.navigate("team_achievements") }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Formation",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        color = Color(0xFF19376D),
                        text = "Formation",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ) },                selected = false,
                onClick = { navController.navigate("soccer_formation") }
            )
        }
    }
}
