package me.tmonteiro.kacn.playground.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tmonteiro.kacn.playground.api.PlaygroundAPI
import me.tmonteiro.kacn.playground.model.card.Card


@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name = "idName") val id: String,
    @Json(name = "rarity") val rarity: String,
    @Json(name = "name") val name: String,
    @Json(name = "elixirCost") val elixirCost: Int,
    @Json(name = "arena") val arena: Int,
    val imageUrl: String = String.format(IMAGE_URL_PATTERN, PlaygroundAPI.API_URL, id)
) {
    companion object {
        private const val IMAGE_URL_PATTERN = "%simages/cards/%s.png"
    }
}

fun CardResponse.toCard() = Card(
    id = id,
    rarity = rarity,
    name = name,
    elixirCost = elixirCost,
    arena = arena,
    imageUrl = imageUrl
)