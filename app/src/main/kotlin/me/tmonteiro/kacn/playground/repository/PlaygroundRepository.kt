package me.tmonteiro.kacn.playground.repository

import me.tmonteiro.kacn.playground.api.PlaygroundAPI
import me.tmonteiro.kacn.playground.api.data.toCard
import me.tmonteiro.kacn.playground.api.data.toCardDetail
import me.tmonteiro.kacn.playground.api.makeAsyncOperation

class PlaygroundRepository(private val playgroundAPI: PlaygroundAPI) {

    fun fetchCard() = makeAsyncOperation {
        playgroundAPI.getCards().await().map { it.toCard() }
    }

    fun fetchCardDetail(id: String) = makeAsyncOperation {
        playgroundAPI.getCardDetail(id).await().toCardDetail()
    }


}