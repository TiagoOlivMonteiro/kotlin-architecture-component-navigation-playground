package me.tmonteiro.kacn.playground.ui.viewholder

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.model.card.Card

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val container = view.findViewById<View>(R.id.item_list_card_container)
    private val name = view.findViewById<TextView>(R.id.tv_name)
    private val icon = view.findViewById<ImageView>(R.id.iv_icon)
    private val rarity = view.findViewById<View>(R.id.view_rarity)
    private val elixirCost = view.findViewById<TextView>(R.id.tv_elixir_cost)
    private val arena = view.findViewById<TextView>(R.id.tv_arena)

    fun bind(card: Card?, drawableRarity: Drawable, animationRequired: Boolean, clickListener: (Card?) -> Unit) {
        name.text = card?.name
        elixirCost.text = String.format("%d", card?.elixirCost)
        arena.text = String.format("Arena %d", card?.arena)
        Picasso.get().load(card?.imageUrl).into(icon)
        rarity.background = drawableRarity
        if (animationRequired) {
            val animationDrawable = rarity.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(2000)
            animationDrawable.start()
        }
        container.setOnClickListener { clickListener(card) }
    }

}