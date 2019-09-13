package me.tmonteiro.kacn.playground.ui.robot

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import br.com.arch.toolkit.livedata.response.MutableResponseLiveData
import io.mockk.every
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.di.repositoryModule
import me.tmonteiro.kacn.playground.model.card.Card
import me.tmonteiro.kacn.playground.repository.PlaygroundRepository
import me.tmonteiro.kacn.playground.ui.CardListFragment

class CardListFragmentTestRobot(private val repository: PlaygroundRepository) {

    infix fun arrange(func: CardListFragmentTestArrangeRobot.() -> Unit): CardListFragmentTestArrangeRobot =
        CardListFragmentTestArrangeRobot(repository).apply(func)

    infix fun act(func: CardListFragmentTestActRobot.() -> Unit): CardListFragmentTestActRobot =
        CardListFragmentTestActRobot().apply(func)


    infix fun assert(func: CardListFragmentTestAssertRobot.() -> Unit): CardListFragmentTestAssertRobot =
        CardListFragmentTestAssertRobot().apply(func)

}

class CardListFragmentTestArrangeRobot(val repository: PlaygroundRepository) {

    fun mockFetchCardSuccess() {
        every { repository.fetchCard() } returns fetchCardSuccessResponse
    }

    private val fetchCardSuccessResponse = MutableResponseLiveData<List<Card>>().apply {
        postData(listOf(Card("id","epic","name", 3,0, "http://justmock.png")))
    }

}

class CardListFragmentTestActRobot {

    fun launchFragment() = launchFragmentInContainer<CardListFragment>(null, R.style.AppTheme)

}

class CardListFragmentTestAssertRobot {}