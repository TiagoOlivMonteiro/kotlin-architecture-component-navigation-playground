package me.tmonteiro.kacn.playground.di

import me.tmonteiro.kacn.playground.viewmodel.CardDetailViewModel
import me.tmonteiro.kacn.playground.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CardViewModel(get()) }
    viewModel { CardDetailViewModel(get()) }
}