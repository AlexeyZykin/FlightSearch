package com.alexisdev.airline_tickets.util

import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.alexisdev.model.Offer
import com.alexisdev.model.Recommendation
import com.alexisdev.model.TicketOffer
import com.alexisdev.model.Tip
import com.alexisdev.resources.R

fun Offer.imgRes(): Int {
    return when(id) {
        1 -> R.drawable.offer_image_1
        2 -> R.drawable.offer_image_2
        3 -> R.drawable.offer_image_3
        else -> R.drawable.offer_placeholder
    }
}

fun Tip.imgRes(): Int {
    return when (id) {
        1 -> R.drawable.ic_complex_route
        2 -> R.drawable.ic_anywhere
        3 -> R.drawable.ic_weekend
        4 -> R.drawable.ic_hot_tickets
        else -> R.drawable.offer_placeholder
    }
}

fun Recommendation.imgRes(): Int {
    return when (id) {
        1 -> R.drawable.img_istanbul
        2 -> R.drawable.img_sochi
        3 -> R.drawable.img_phuket
        else -> R.drawable.offer_placeholder
    }
}

fun TicketOffer.imgRes(): Int {
    return when (id) {
        1 -> R.drawable.ic_red_circle
        10 -> R.drawable.ic_blue_circle
        30 -> R.drawable.ic_white_circle
        else -> R.drawable.ic_red_circle
    }
}

fun Int.offerPriceFormat(): String {
    return String.format("%,d", this).replace(',', ' ')
}


fun EditText.onDrawableEndClick(action: () -> Unit) {
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            v as EditText
            val end = if (v.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL)
                v.left else v.right
            if (event.rawX >= (end - v.compoundPaddingEnd)) {
                action.invoke()
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }
}