package me.tmonteiro.kacn.playground.viewmodel

import androidx.lifecycle.ViewModel
import br.com.arch.toolkit.livedata.response.ResponseLiveData
import br.com.arch.toolkit.livedata.response.SwapResponseLiveData
import me.tmonteiro.kacn.playground.model.card.Card
import me.tmonteiro.kacn.playground.repository.PlaygroundRepository

class CardViewModel(private val repository: PlaygroundRepository) : ViewModel() {

    private val swapCardLiveData = SwapResponseLiveData<List<Card>>()

    val responseCardDataLiveData: ResponseLiveData<List<Card>>
        get() {
            return swapCardLiveData
        }

    fun fetchCard() {
        swapCardLiveData.swapSource(
            repository.fetchCard(),
            true
        )
    }

}