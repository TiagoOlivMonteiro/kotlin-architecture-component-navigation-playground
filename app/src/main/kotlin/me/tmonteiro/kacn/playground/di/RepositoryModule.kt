package me.tmonteiro.kacn.playground.di

import me.tmonteiro.kacn.playground.repository.PlaygroundRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PlaygroundRepository(get()) }
}