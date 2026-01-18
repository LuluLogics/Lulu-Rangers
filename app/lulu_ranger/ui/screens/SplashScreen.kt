package com.lulu_ranger.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lulu_ranger.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var scale by remember { mutableStateOf(1f) }
    var alpha by remember { mutableStateOf(1f) }
    var bubbleBurst by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(1000)

        scale = 3f
        alpha = 0f
        delay(500)

        bubbleBurst = false
        navController.navigate("player_list") {
            popUpTo("splash") { inclusive = true }
        }
    }

    AnimatedVisibility(
        visible = bubbleBurst,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.squad),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Lulu Rangers Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale)
                    .alpha(alpha),
                contentScale = ContentScale.Fit
            )
        }
    }
}
