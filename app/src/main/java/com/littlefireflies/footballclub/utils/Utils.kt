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
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("dd MMMM yyyy")
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
    try {
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val gmtTime = formatter.parse(datetime)
        formatter.timeZone = TimeZone.getDefault()

        return formatter.format(gmtTime)
    } catch (e: java.lang.Exception) {
        return "Date Invalid/Not Found"
    }
}

fun bornDateFormatter(inputDate: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("dd MMMM yyyy")
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