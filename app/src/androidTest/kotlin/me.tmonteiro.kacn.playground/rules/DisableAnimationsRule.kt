package me.tmonteiro.kacn.playground.ui.rules

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.IOException

class DisableAnimationsRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val isTestRunningOnJvm = try {
                    Class.forName("org.robolectric.Robolectric") != null
                } catch (exception: ClassNotFoundException) {
                    false
                }

                disableAnimations(isTestRunningOnJvm)
                try {
                    base.evaluate()
                } finally {
                    enableAnimations(isTestRunningOnJvm)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun disableAnimations(isTestRunningOnJvm: Boolean) {
        if (!isTestRunningOnJvm) {
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global transition_animation_scale 0")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global window_animation_scale 0")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global animator_duration_scale 0")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put secure show_ime_with_hard_keyboard 0")
        }
    }

    @Throws(IOException::class)
    private fun enableAnimations(isTestRunningOnJvm: Boolean) {
        if (!isTestRunningOnJvm) {
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global transition_animation_scale 1")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global window_animation_scale 1")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put global animator_duration_scale 1")
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand("settings put secure show_ime_with_hard_keyboard 1")
        }
    }
}