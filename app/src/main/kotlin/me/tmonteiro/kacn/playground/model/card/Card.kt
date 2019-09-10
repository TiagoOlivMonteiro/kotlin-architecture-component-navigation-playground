package me.tmonteiro.kacn.playground.model.card

data class Card(
    val id: String, val rarity: String, val name: String,
    val elixirCost: Int, val arena: Int, val imageUrl: String
)
