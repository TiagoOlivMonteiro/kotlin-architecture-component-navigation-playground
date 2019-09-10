package me.tmonteiro.kacn.playground.factory

import com.squareup.moshi.Moshi

object MoshiFactory {

    fun build(): Moshi {
        return Moshi.Builder().build()
    }
}