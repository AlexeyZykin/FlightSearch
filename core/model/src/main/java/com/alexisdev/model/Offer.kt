package com.alexisdev.model

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)