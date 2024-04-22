package com.alexisdev.data

import com.alexisdev.model.FlightInfo
import com.alexisdev.model.HandLuggage
import com.alexisdev.model.Luggage
import com.alexisdev.model.Offer
import com.alexisdev.model.Price
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import com.alexisdev.network.model.FlightInfoDTO
import com.alexisdev.network.model.HandLuggageDTO
import com.alexisdev.network.model.LuggageDTO
import com.alexisdev.network.model.OfferDTO
import com.alexisdev.network.model.PriceDTO
import com.alexisdev.network.model.TicketDTO
import com.alexisdev.network.model.TicketOfferDTO

fun OfferDTO.toOffer() = Offer(
    id = id,
    title = title,
    town = town,
    price = price.toPrice()
)

fun PriceDTO.toPrice() = Price(value)

fun TicketOfferDTO.toTicketOffer() = TicketOffer(
    id = id,
    title = title,
    timeRange = timeRange,
    price = price.toPrice()
)

fun TicketDTO.toTicket() = Ticket(
    id = id,
    badge = badge,
    price = price.toPrice(),
    providerName = providerName,
    company = company,
    departure = departure.toFlightInfo(),
    arrival = arrival.toFlightInfo(),
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    luggage = luggage.toLuggage(),
    handLuggage = handLuggage.toHandLuggage(),
    isReturnable = isReturnable,
    isExchangeable = isExchangeable
)

fun FlightInfoDTO.toFlightInfo() = FlightInfo(
    town = town,
    date = date,
    airport = airport
)

fun LuggageDTO.toLuggage() = Luggage(
    hasLuggage = hasLuggage, price = price?.toPrice()
)

fun HandLuggageDTO.toHandLuggage() = HandLuggage(
    hasHandLuggage = hasHandLuggage, size = size
)