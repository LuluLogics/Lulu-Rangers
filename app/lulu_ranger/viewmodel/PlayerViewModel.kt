package com.lulu_ranger.viewmodel

import androidx.lifecycle.ViewModel
import com.lulu_ranger.R
import com.lulu_ranger.data.model.Player

class PlayerViewModel : ViewModel() {

    val players = listOf(
        Player(
            id = 1,
            name = "Carles Puyol",
            position = "Defender",
            goals = 5,
            assists = 2,
            imageRes = R.drawable.puyol,
            age = 43,
            nationality = "Spain",
            height = "178 cm",
            weight = "80 kg",
            appearances = 593,
            yellowCards = 85,
            redCards = 7
        ),
        Player(
            id = 2,
            name = "Virgil van Dijk",
            position = "Defender",
            goals = 8,
            assists = 3,
            imageRes = R.drawable.virgil_van_dijk,
            age = 32,
            nationality = "Netherlands",
            height = "193 cm",
            weight = "92 kg",
            appearances = 400,
            yellowCards = 25,
            redCards = 2
        ),
        Player(
            id = 3,
            name = "Alessandro Nesta",
            position = "Defender",
            goals = 3,
            assists = 5,
            imageRes = R.drawable.nesta,
            age = 47,
            nationality = "Italy",
            height = "187 cm",
            weight = "82 kg",
            appearances = 621,
            yellowCards = 40,
            redCards = 5
        ),
        Player(
            id = 4,
            name = "Antonio Rudiger",
            position = "Defender",
            goals = 2,
            assists = 1,
            imageRes = R.drawable.rudiger,
            age = 30,
            nationality = "Germany",
            height = "190 cm",
            weight = "85 kg",
            appearances = 350,
            yellowCards = 50,
            redCards = 3
        ),
        Player(
            id = 5,
            name = "Doctor Khumalo",
            position = "Midfielder",
            goals = 15,
            assists = 20,
            imageRes = R.drawable.dr,
            age = 55,
            nationality = "South Africa",
            height = "172 cm",
            weight = "70 kg",
            appearances = 400,
            yellowCards = 30,
            redCards = 1
        ),
        Player(
            id = 6,
            name = "Zinedine Zidane",
            position = "Midfielder",
            goals = 7,
            assists = 10,
            imageRes = R.drawable.zidane,
            age = 52,
            nationality = "France",
            height = "185 cm",
            weight = "80 kg",
            appearances = 506,
            yellowCards = 65,
            redCards = 14
        ),
        Player(
            id = 7,
            name = "Pelé",
            position = "Forward",
            goals = 40,
            assists = 5,
            imageRes = R.drawable.pele,
            age = 82,  // Historical age
            nationality = "Brazil",
            height = "173 cm",
            weight = "73 kg",
            appearances = 831,
            yellowCards = 3,
            redCards = 0
        ),
        Player(
            id = 8,
            name = "Diego Maradona",
            position = "Forward",
            goals = 35,
            assists = 7,
            imageRes = R.drawable.maradona,
            age = 60,  // Historical age
            nationality = "Argentina",
            height = "165 cm",
            weight = "75 kg",
            appearances = 590,
            yellowCards = 40,
            redCards = 11
        ),
        Player(
            id = 9,
            name = "Leroy Sané",
            position = "Winger",
            goals = 12,
            assists = 8,
            imageRes = R.drawable.leroy_sane,
            age = 28,
            nationality = "Germany",
            height = "183 cm",
            weight = "75 kg",
            appearances = 280,
            yellowCards = 10,
            redCards = 1
        ),
        Player(
            id = 10,
            name = "Lionel Messi",
            position = "Winger",
            goals = 30,
            assists = 12,
            imageRes = R.drawable.messi,
            age = 36,
            nationality = "Argentina",
            height = "170 cm",
            weight = "72 kg",
            appearances = 1020,
            yellowCards = 85,
            redCards = 3
        )
    )

    fun getPlayerById(id: Int): Player? {
        return players.find { it.id == id }
    }
}
