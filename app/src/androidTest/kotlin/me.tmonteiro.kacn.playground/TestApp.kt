package me.tmonteiro.kacn.playground

import android.app.Application
import android.content.Context
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.runner.AndroidJUnitRunner
import me.tmonteiro.kacn.playground.factory.PlaygroundAPIFactory
import me.tmonteiro.kacn.playground.factory.RetrofitFactory
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}

class TestApp : PlaygroundApp() {

    var idlingResource: OkHttp3IdlingResource? = null

    var baseUrl: HttpUrl = "https://localhost".toHttpUrl()
        set(value) {
            loadKoinModules(module(override = true) {
                single {
                    val builder = OkHttpClient.Builder()
                    builder.addInterceptor(object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            return chain.proceed(
                                chain.request().newBuilder()
                                    .url(
                                        chain.request().url
                                            .newBuilder()
                                            .host(value.host)
                                            .port(value.port)
                                            .build()
                                    )
                                    .build()
                            )
                        }
                    })
                    builder.addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    builder.build().apply {
                        idlingResource = OkHttp3IdlingResource(this.dispatcher)
                        IdlingRegistry.getInstance().register(idlingResource)
                    }
                }
                single { RetrofitFactory.build(baseUrl.toString(), get(), get()) }
                single { PlaygroundAPIFactory.build(get()) }
            })
            field = value
        }

}

class OkHttp3IdlingResource constructor(private val dispatcher: Dispatcher) : IdlingResource {

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null

    init {
        dispatcher.idleCallback = Runnable {
            val callback = this@OkHttp3IdlingResource.callback
            callback?.onTransitionToIdle()
        }
    }

    override fun getName() = "OkHttp"

    override fun isIdleNow() = dispatcher.runningCallsCount() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }
}
