package me.tmonteiro.kacn.playground.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tmonteiro.kacn.playground.api.PlaygroundAPI
import me.tmonteiro.kacn.playground.model.carddetail.CardDetail


@JsonClass(generateAdapter = true)
data class CardDetailResponse(
    @Json(name = "idName") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "rarity") val rarity: String,
    @Json(name = "type") val type: String,
    val imageUrl: String = String.format(
        IMAGE_URL_PATTERN, PlaygroundAPI.API_URL, id
    )
) {
    companion object {
        private const val IMAGE_URL_PATTERN = "%simages/cards/%s.png"
    }
}

fun CardDetailResponse.toCardDetail() = CardDetail(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    rarity = rarity,
    type = type
)