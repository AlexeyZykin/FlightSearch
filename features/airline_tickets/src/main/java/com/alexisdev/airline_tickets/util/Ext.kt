package com.alexisdev.airline_tickets.util

import com.alexisdev.resources.R
import com.alexisdev.model.Offer

fun Offer.imgRes(id: Int): Int {
    return when(id) {
        1 -> R.drawable.offer_image_1
        2 -> R.drawable.offer_image_2
        3 -> R.drawable.offer_image_3
        else -> R.drawable.offer_placeholder
    }
}