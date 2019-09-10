package me.tmonteiro.kacn.playground.di

import me.tmonteiro.kacn.playground.api.PlaygroundAPI
import me.tmonteiro.kacn.playground.factory.MoshiFactory
import me.tmonteiro.kacn.playground.factory.OkHttpFactory
import me.tmonteiro.kacn.playground.factory.PlaygroundAPIFactory
import me.tmonteiro.kacn.playground.factory.RetrofitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { OkHttpFactory.build(androidContext()) }
    single { MoshiFactory.build() }
    single { RetrofitFactory.build(PlaygroundAPI.API_URL, get(), get()) }
    single { PlaygroundAPIFactory.build(get()) }
}