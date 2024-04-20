package com.alexisdev.data

import com.alexisdev.model.Offer
import com.alexisdev.model.Price
import com.alexisdev.network.model.OfferDTO
import com.alexisdev.network.model.PriceDTO

fun OfferDTO.toOffer() = Offer(
    id = id,
    title = title,
    town = town,
    price = price.toPrice()
)

fun PriceDTO.toPrice() = Price(value)