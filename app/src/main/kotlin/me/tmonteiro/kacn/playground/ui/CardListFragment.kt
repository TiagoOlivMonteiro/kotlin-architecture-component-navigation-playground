package me.tmonteiro.kacn.playground.ui

import android.os.Bundle
import android.view.View
import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.model.card.Card
import me.tmonteiro.kacn.playground.ui.adapter.CardAdapter
import me.tmonteiro.kacn.playground.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardListFragment : Fragment(R.layout.fragment_card_list) {

    private val viewModel by viewModel<CardViewModel>()
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnRetry: AppCompatButton

    companion object {
        const val SHOW_LOADING = 0
        const val SHOW_ERROR = 1
        const val SHOW_CONTENT = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById(R.id.view_flipper)
        recyclerView = view.findViewById(R.id.rv_card)
        btnRetry = view.findViewById(R.id.btn_retry)
        btnRetry.setOnClickListener { fetchCard() }
    }

    override fun onResume() {
        super.onResume()
        fetchCard()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.responseCardDataLiveData.observe(viewLifecycleOwner) {
            loading {
                showLoading()
            }
            error { _ ->
                showError()
            }
            data {
                showContent(it)
            }
        }
    }

    private fun showContent(items: List<Card>) {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.adapter = CardAdapter(requireContext(), items) { selected -> this navigateToCardDetail selected }
        viewFlipper.displayedChild = SHOW_CONTENT
    }

    private fun showLoading() {
        viewFlipper.displayedChild = SHOW_LOADING
    }

    private fun showError() {
        viewFlipper.displayedChild = SHOW_ERROR
    }

    private fun fetchCard() = viewModel.fetchCard()

    private infix fun navigateToCardDetail(card: Card?) {
        card?.let {
            val direction = CardListFragmentDirections.actionDetail(card.id)
            NavHostFragment.findNavController(this).navigate(direction)
        }
    }

}