package me.tmonteiro.kacn.playground

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestRunnerAbc : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
//        DexOpener.install(this)
        return super.newApplication(cl, App::class.java.name, context)
    }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        AndroidThreeTen.init(this)
    }
}
