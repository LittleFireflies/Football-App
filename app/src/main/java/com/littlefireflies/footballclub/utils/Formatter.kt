package com.littlefireflies.footballclub.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by widyarso.purnomo on 04/09/2018.
 */

fun dateFormatter(inputDate: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("E, dd MMM yyyy")
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