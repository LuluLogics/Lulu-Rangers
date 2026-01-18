package com.lulu_ranger.data.model

data class Player(
    val id: Int,
    val name: String,
    val position: String,
    val goals: Int,
    val assists: Int,
    val imageRes: Int,
    val age: Int,
    val nationality: String,
    val height: String,
    val weight: String,
    val appearances: Int,
    val redCards: Int,
    val yellowCards: Int
)
