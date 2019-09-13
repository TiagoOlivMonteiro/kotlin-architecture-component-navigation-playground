package me.tmonteiro.kacn.playground.ui.rules

import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class KoinRule(private val modules: List<Module> = emptyList()) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {

                try {
                    startKoin {
                        androidContext(ApplicationProvider.getApplicationContext())
                        modules(modules)
                    }
                    base.evaluate()
                } finally {
                    stopKoin()
                }
            }
        }
    }
}