/*
 * Copyright 2022 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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