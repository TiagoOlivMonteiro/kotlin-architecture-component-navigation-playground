package me.tmonteiro.kacn.playground.model.carddetail

data class CardDetail(
    val id: String, val name: String,
    val description: String, val imageUrl: String,
    val rarity: String, val type: String
)