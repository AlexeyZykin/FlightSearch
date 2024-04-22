package com.alexisdev.data.repository

import com.alexisdev.data.toOffer
import com.alexisdev.data.toTicket
import com.alexisdev.data.toTicketOffer
import com.alexisdev.model.Offer
import com.alexisdev.model.Recommendation
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import com.alexisdev.model.Tip
import com.alexisdev.model.TipAction
import com.alexisdev.network.FlightsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FlightsRepositoryImpl(private val flightsApi: FlightsApi) : FlightsRepository {
    override fun fetchOffers(): Flow<List<Offer>> = flow {
        val offers = flightsApi.fetchOffers().offers
            .map { offerDTO ->
                offerDTO.toOffer()
            }
        emit(offers)
    }

    override fun fetchTicketOffers(): Flow<List<TicketOffer>> = flow {
        val ticketsOffers = flightsApi.fetchTicketOffers().ticketOffers
            .map { ticketOfferDTO ->
                ticketOfferDTO.toTicketOffer()
            }
        emit(ticketsOffers)
    }

    override fun fetchTickets(): Flow<List<Ticket>> = flow {
        val tickets = flightsApi.fetchTickets().tickets
            .map { ticketDTO ->
                ticketDTO.toTicket()
            }
        emit(tickets)
    }

    override fun fetchRecommendations(): Flow<List<Recommendation>> = flow {
        val responseApi = listOf(
            Recommendation(id = 1, town = "Стамбул", subtitle = "Популярное направление"),
            Recommendation(id = 2, town = "Сочи", subtitle = "Популярное направление"),
            Recommendation(id = 3, town = "Пхукет", subtitle = "Популярное направление"),
        )
        emit(responseApi)
    }

    override suspend fun getSearchTips(): List<Tip> {
        return listOf(
            Tip(id = 1, title = "Сложный маршрут", TipAction.COMPLEX_ROUTE),
            Tip(id = 2, title = "Куда угодно", TipAction.ANYWHERE),
            Tip(id = 3, title = "Выходные", TipAction.WEEKEND),
            Tip(id = 4, title = "Горячие билеты", TipAction.HOT_TICKETS)
        )
    }
}