package me.tmonteiro.kacn.playground.viewmodel

import androidx.lifecycle.ViewModel
import br.com.arch.toolkit.livedata.response.ResponseLiveData
import br.com.arch.toolkit.livedata.response.SwapResponseLiveData
import me.tmonteiro.kacn.playground.model.card.Card
import me.tmonteiro.kacn.playground.model.carddetail.CardDetail
import me.tmonteiro.kacn.playground.repository.PlaygroundRepository

class CardDetailViewModel(private val repository: PlaygroundRepository) : ViewModel() {

    private val swapCardDetailLiveData = SwapResponseLiveData<CardDetail>()

    val responseCardDetailDataLiveData: ResponseLiveData<CardDetail>
        get() {
            return swapCardDetailLiveData
        }

    fun fetchCardDetail(id: String) {
        swapCardDetailLiveData.swapSource(
            repository.fetchCardDetail(id),
            true
        )
    }

}