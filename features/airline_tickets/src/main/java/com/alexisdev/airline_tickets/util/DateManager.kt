package com.alexisdev.airline_tickets.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateManager {
    const val INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    const val OUTPUT_DATE_FORMAT = "H:mm"
    const val DATE_FORMAT = "d MMM, E"

    fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
        val outputFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())

        val date: Date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }

    fun calculateFlightDuration(departure: String, arrival: String): String {
        val inputFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())

        val departureDate: Date = inputFormat.parse(departure)
        val arrivalDate: Date = inputFormat.parse(arrival)

        val durationMillis = arrivalDate.time - departureDate.time
        val durationHours = TimeUnit.MILLISECONDS.toMinutes(durationMillis) / 60.0
        val result = String.format("%.1f", durationHours)
        return "$result ч"
    }

    fun convertDateToString(date: Date): String{
        val df = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return df.format(date)
    }

    fun currentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val time = Calendar.getInstance().time
        return sdf.format(time)
    }
}