package com.lulu_ranger.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.lulu_ranger.data.model.Player

class FavouriteViewModel : ViewModel() {
    private val _favouritePlayers = mutableStateListOf<Player>()
    val favouritePlayers: List<Player> get() = _favouritePlayers

    fun addToFavourites(player: Player) {
        if (player !in _favouritePlayers) {
            _favouritePlayers.add(player)
        }
    }

    fun removeFromFavourites(player: Player) {
        _favouritePlayers.remove(player)
    }

    fun toggleFavourite(player: Player) {
        if (_favouritePlayers.contains(player)) {
            removeFromFavourites(player)
        } else {
            addToFavourites(player)
        }
    }
}
