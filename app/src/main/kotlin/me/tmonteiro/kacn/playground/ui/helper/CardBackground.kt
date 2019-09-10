package me.tmonteiro.kacn.playground.ui.helper

import android.content.Context
import android.graphics.drawable.Drawable
import me.tmonteiro.kacn.playground.R
import me.tmonteiro.kacn.playground.ui.adapter.CardAdapter.Companion.CARD_RARITY_COMMON
import me.tmonteiro.kacn.playground.ui.adapter.CardAdapter.Companion.CARD_RARITY_EPIC
import me.tmonteiro.kacn.playground.ui.adapter.CardAdapter.Companion.CARD_RARITY_LEGENDARY
import me.tmonteiro.kacn.playground.ui.adapter.CardAdapter.Companion.CARD_RARITY_RARE

fun getBackgroundAndAnimationRequiredFlag(context: Context, rarity: String?): Pair<Drawable, Boolean> {
    return when (rarity) {
        CARD_RARITY_COMMON -> Pair(context.getDrawable(R.drawable.card_common), false)
        CARD_RARITY_RARE -> Pair(context.getDrawable(R.drawable.card_rare), false)
        CARD_RARITY_EPIC -> Pair(context.getDrawable(R.drawable.card_epic), false)
        CARD_RARITY_LEGENDARY -> Pair(context.getDrawable(R.drawable.card_legendary), true)
        else -> Pair(context.getDrawable(R.drawable.card_common), false)
    }
}