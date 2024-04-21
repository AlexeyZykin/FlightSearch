package com.alexisdev.model

data class Tip(
    val id: Int,
    val title: String,
    val tipAction: TipAction
)

enum class TipAction {
    COMPLEX_ROUTE,
    ANYWHERE,
    WEEKEND,
    HOT_TICKETS
}