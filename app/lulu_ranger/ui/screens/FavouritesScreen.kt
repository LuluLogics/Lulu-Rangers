package com.lulu_ranger.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lulu_ranger.R
import com.lulu_ranger.ui.components.BottomNavigationBar
import com.lulu_ranger.ui.components.PlayerCard
import com.lulu_ranger.viewmodel.FavouriteViewModel

@Composable
fun FavouritesScreen(
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel
) {
    val favoritePlayers = favouriteViewModel.favouritePlayers

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.squad),
            contentDescription = "Squad Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (favoritePlayers.isEmpty()) {
                    item {
                        Text(
                            text = "No favorite players added.",
                            modifier = Modifier.padding(16.dp),
                            color = Color.Yellow,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    items(favoritePlayers) { player ->
                        PlayerCard(
                            player = player,
                            isFavorite = true,
                            onFavoriteToggle = { favouriteViewModel.toggleFavourite(player) },
                            onClick = { navController.navigate("player_detail/${player.id}") }
                        )
                    }
                }
            }
        }
    }
}
