package me.tmonteiro.kacn.playground.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.model.card.Card
import me.tmonteiro.kacn.playground.ui.helper.getBackgroundAndAnimationRequiredFlag
import me.tmonteiro.kacn.playground.ui.viewholder.CardViewHolder

class CardAdapter(
    private val context: Context,
    private val items: List<Card>?,
    private  val clickListener: (Card?) -> Unit
) : RecyclerView.Adapter<CardViewHolder>() {

    companion object {
        const val CARD_RARITY_COMMON = "Common"
        const val CARD_RARITY_RARE = "Rare"
        const val CARD_RARITY_EPIC = "Epic"
        const val CARD_RARITY_LEGENDARY = "Legendary"
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.view_item_list_card,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items?.get(position)
        item.let {
            val background = getBackgroundAndAnimationRequiredFlag(context, it?.rarity)
            holder.bind(it, background.first, background.second, clickListener)
        }
    }

}