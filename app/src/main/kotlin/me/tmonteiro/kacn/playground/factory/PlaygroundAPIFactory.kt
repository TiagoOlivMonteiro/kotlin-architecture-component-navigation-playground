package me.tmonteiro.kacn.playground.factory

import me.tmonteiro.kacn.playground.api.PlaygroundAPI
import retrofit2.Retrofit

object PlaygroundAPIFactory {

    fun build(retrofit: Retrofit) : PlaygroundAPI {
        return retrofit.create(PlaygroundAPI::class.java)
    }
}