package me.tmonteiro.kacn.playground.api

import kotlinx.coroutines.Deferred
import me.tmonteiro.kacn.playground.api.data.CardDetailResponse
import me.tmonteiro.kacn.playground.api.data.CardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaygroundAPI {

    companion object {
        const val API_URL = "http://www.clashapi.xyz/"
    }

    @GET("/api/cards")
    fun getCards(): Deferred<List<CardResponse>>

    @GET("/api/cards/{idName}")
    fun getCardDetail(@Path("idName") cardId: String): Deferred<CardDetailResponse>
}