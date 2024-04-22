package com.alexisdev.data.repository

import com.alexisdev.common.Response
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
    override fun fetchOffers(): Flow<Response<List<Offer>>> = flow {
        emit(Response.Loading())
        val apiResponse = try {
            flightsApi.fetchOffers().offers
        } catch (e: Exception) {
            emit(Response.Error(msg = "No internet connection"))
            null
        }

        if (apiResponse != null) {
            val offers = apiResponse
                .map { offerDTO ->
                    offerDTO.toOffer()
                }
            emit(Response.Success(offers))
        }
    }

    override fun fetchTicketOffers(): Flow<Response<List<TicketOffer>>> = flow {
        emit(Response.Loading())
        val apiResponse = try {
            flightsApi.fetchTicketOffers().ticketOffers
        } catch (e: Exception) {
            emit(Response.Error(msg = "No internet connection"))
            null
        }

        if (apiResponse != null) {
            val ticketsOffers = apiResponse
                .map { ticketOfferDTO ->
                    ticketOfferDTO.toTicketOffer()
                }
            emit(Response.Success(ticketsOffers))
        }
    }

    override fun fetchTickets(): Flow<Response<List<Ticket>>> = flow {
        emit(Response.Loading())
        val apiResponse = try {
            flightsApi.fetchTickets().tickets
        } catch (e: Exception) {
            emit(Response.Error(msg = "No internet connection"))
            null
        }

        if (apiResponse != null) {
            val tickets = apiResponse
                .map { ticketDTO ->
                    ticketDTO.toTicket()
                }
            emit(Response.Success(tickets))
        }
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