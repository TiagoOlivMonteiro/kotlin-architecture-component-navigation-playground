package me.tmonteiro.kacn.playground.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.model.carddetail.CardDetail
import me.tmonteiro.kacn.playground.ui.helper.getBackgroundAndAnimationRequiredFlag
import me.tmonteiro.kacn.playground.viewmodel.CardDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailFragment : Fragment(R.layout.fragment_card_detail) {

    private val viewModel by viewModel<CardDetailViewModel>()
    private val args: CardDetailFragmentArgs by navArgs()
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var ivIcon: ImageView
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvRarity: AppCompatTextView
    private lateinit var tvType: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var viewRarity: View
    private lateinit var btnRetry: AppCompatButton

    companion object {
        const val SHOW_LOADING = 0
        const val SHOW_ERROR = 1
        const val SHOW_CONTENT = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById(R.id.view_flipper)
        btnRetry = view.findViewById(R.id.btn_retry)
        ivIcon = view.findViewById(R.id.iv_icon)
        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)
        tvRarity = view.findViewById(R.id.tv_rarity)
        tvType = view.findViewById(R.id.tv_type)
        viewRarity = view.findViewById(R.id.rarity_type_container)
        btnRetry.setOnClickListener { fetchCardDetail() }
    }

    override fun onResume() {
        super.onResume()
        fetchCardDetail()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.responseCardDetailDataLiveData.observe(viewLifecycleOwner) {
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

    private fun showContent(cardDetail: CardDetail) {
        tvDescription.text = cardDetail.description
        tvTitle.text = cardDetail.name
        tvType.text = cardDetail.type
        tvRarity.text = cardDetail.rarity
        Picasso.get().load(cardDetail.imageUrl).into(ivIcon)

        val background = getBackgroundAndAnimationRequiredFlag(requireContext(), cardDetail.rarity)
        viewRarity.background = background.first
        if (background.second) {
            val animationDrawable = viewRarity.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(2000)
            animationDrawable.start()
        }
        viewFlipper.displayedChild = SHOW_CONTENT
    }

    private fun showLoading() {
        viewFlipper.displayedChild = SHOW_LOADING
    }

    private fun showError() {
        viewFlipper.displayedChild = SHOW_ERROR
    }

    private fun fetchCardDetail() = viewModel.fetchCardDetail(args.id)

}
