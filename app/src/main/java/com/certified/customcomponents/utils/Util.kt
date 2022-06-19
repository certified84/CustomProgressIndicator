package com.certified.customcomponents.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

fun currentDate(): Calendar {
    return Calendar.getInstance()
}

fun formatDate(date: Long): String {
    val dateString = DateUtils.getRelativeTimeSpanString(
        date,
        currentDate().timeInMillis,
        DateUtils.SECOND_IN_MILLIS
    ).toString()
    return when {
        "minute" in dateString -> {
            SimpleDateFormat("h:mm a", Locale.getDefault()).format(date)
        }
        " seconds" in dateString -> {
            "now"
        }
        else -> dateString
    }
}

fun formatReminderDate(date: Long): String =
    SimpleDateFormat("dd MMM, yyyy h:mm a", Locale.getDefault()).format(date)