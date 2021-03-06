package com.littlefireflies.footballclub.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */

fun getTimeMillis(datetime: String) : Long{
    var millisecond = 0L
    val dateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm")
    try {
        val date = dateFormat.parse(datetime)
        millisecond = date.time
    } catch (e : Exception) {
        e.printStackTrace()
    }

    return millisecond
}

fun dateFormatter(inputDate: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val beforeFormat: Date
    var afterFormat = ""

    try {
        beforeFormat = inputFormat.parse(inputDate)
        afterFormat = outputFormat.format(beforeFormat)
    } catch ( e: ParseException) {
        e.printStackTrace()
    }

    return afterFormat
}

fun timeFormatter(inputTime: String?): String {
    val time = inputTime?.split(":")
    return "${time?.get(0)}:${time?.get(1)}"
}

fun toGmtFormat(datetime: String): String {
    return try {
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val gmtTime = formatter.parse(datetime)
        formatter.timeZone = TimeZone.getDefault()

        formatter.format(gmtTime)
    } catch (e: java.lang.Exception) {
        "Date Invalid/Not Found"
    }
}

fun bornDateFormatter(inputDate: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val beforeFormat: Date
    var afterFormat = ""

    try {
        beforeFormat = inputFormat.parse(inputDate)
        afterFormat = outputFormat.format(beforeFormat)
    } catch ( e: ParseException) {
        e.printStackTrace()
    }

    return afterFormat
}