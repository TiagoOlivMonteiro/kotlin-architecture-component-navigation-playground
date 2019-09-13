package me.tmonteiro.kacn.playground

import android.app.Application
import me.tmonteiro.kacn.playground.di.networkModule
import me.tmonteiro.kacn.playground.di.repositoryModule
import me.tmonteiro.kacn.playground.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class PlaygroundApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules(
                listOf(networkModule, repositoryModule, viewModelModule)
            )
            androidContext(this@PlaygroundApp)
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}