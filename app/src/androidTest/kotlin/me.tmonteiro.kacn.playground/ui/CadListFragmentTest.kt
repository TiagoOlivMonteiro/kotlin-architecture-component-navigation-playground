package me.tmonteiro.kacn.playground.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import me.tmonteiro.kacn.playground.repository.PlaygroundRepository
import me.tmonteiro.kacn.playground.ui.robot.CardListFragmentTestRobot
import me.tmonteiro.kacn.playground.ui.rules.DisableAnimationsRule
import me.tmonteiro.kacn.playground.ui.rules.KoinRule
import me.tmonteiro.kacn.playground.viewmodel.CardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class CadListFragmentTest {

    @JvmField
    @Rule
    val disableAnimationsRule = DisableAnimationsRule()

    @JvmField
    @Rule
    val koinRule = KoinRule()

    private val repository: PlaygroundRepository = mockk()
    private val robot = CardListFragmentTestRobot(repository)
    private val viewModel = CardViewModel(repository)

    @Before
    fun setup() {
        loadKoinModules(module {
            viewModel(override = true) { viewModel }
        })
    }

    @Test
    fun test() {
        robot.apply {
            arrange {
                mockFetchCardSuccess()
            }
            act {
                launchFragment()
            }
        }
    }

}