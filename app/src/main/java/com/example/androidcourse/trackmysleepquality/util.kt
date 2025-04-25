/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidcourse.trackmysleepquality

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.androidcourse.R
import com.example.androidcourse.trackmysleepquality.database.SleepNight
import java.text.SimpleDateFormat

/**
 * These functions create a formatted string that can be set in a TextView.
 */

/**
 * Returns a string representing the numeric quality rating.
 */
fun convertNumericQualityToString(quality: Int, resources: Resources): String {
    var qualityString = resources.getString(R.string.three_ok)
    when (quality) {
        -1 -> qualityString = "--"
        0 -> qualityString = resources.getString(R.string.zero_very_bad)
        1 -> qualityString = resources.getString(R.string.one_poor)
        2 -> qualityString = resources.getString(R.string.two_soso)
        4 -> qualityString = resources.getString(R.string.four_pretty_good)
        5 -> qualityString = resources.getString(R.string.five_excellent)
    }
    return qualityString
}


/**
 * Take the Long milliseconds returned by the system and stored in Room,
 * and convert it to a nicely formatted string for display.
 *
 * EEEE - Display the long letter version of the weekday
 * MMM - Display the letter abbreviation of the nmotny
 * dd-yyyy - day in month and full year numerically
 * HH:mm - Hours and minutes in 24hr format
 */
@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}

/**
 * Takes a list of SleepNights and converts and formats it into one string for display.
 *
 * For display in a TextView, we have to supply one string, and styles are per TextView, not
 * applicable per word. So, we build a formatted string using HTML. This is handy, but we will
 * learn a better way of displaying this data in a future lesson.
 *
 * @param   nights - List of all SleepNights in the database.
 * @param   resources - Resources object for all the resources defined for our app.
 *
 * @return  Spanned - An interface for text that has formatting attached to it.
 *           See: https://developer.android.com/reference/android/text/Spanned
 */
/*fun formatNights(nights: String?, resources: Resources?): Spanned {
    val sb = StringBuilder()*/
    fun formatNights(nights: List<SleepNight>, resources: Resources?): Spanned {
        val sb = StringBuilder()
        sb.apply {
            // Check if resources is not null before accessing it
            resources?.let {
                append(it.getString(R.string.title))
                nights.forEach { night ->
                    append("<br>")
                    append(it.getString(R.string.start_time))
                    append("\t${convertLongToDateString(night.StartTimeMili)}<br>")
                    if (night.EndTimeMili != night.StartTimeMili) {
                        append(it.getString(R.string.end_time))
                        append("\t${convertLongToDateString(night.EndTimeMili)}<br>")
                        append(it.getString(R.string.quality))
                        append("\t${convertNumericQualityToString(night.SleepQuality, it)}<br>")
                        append(it.getString(R.string.hours_slept))
                        // Hours
                        append("\t ${night.EndTimeMili.minus(night.StartTimeMili) / 1000 / 60 / 60}:")
                        // Minutes
                        append("${night.EndTimeMili.minus(night.StartTimeMili) / 1000 / 60}:")
                        // Seconds
                        append("${night.EndTimeMili.minus(night.StartTimeMili) / 1000}<br><br>")
                    }
                }
            } ?: run {
                // Handle the case where resources is null (optional)
                append("Resources are not available.")
            }
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    /*sb.apply {
        append(resources.getString(R.string.title))
        nights.forEach {
            append("<br>")
            append(resources.getString(R.string.start_time))
            append("\t${convertLongToDateString(it.StartTimeMili)}<br>")
            if (it.EndTimeMili != it.StartTimeMili) {
                append(resources.getString(R.string.end_time))
                append("\t${convertLongToDateString(it.EndTimeMili)}<br>")
                append(resources.getString(R.string.quality))
                append("\t${convertNumericQualityToString(it.SleepQuality, resources)}<br>")
                append(resources.getString(R.string.hours_slept))
                // Hours
                append("\t ${it.EndTimeMili.minus(it.StartTimeMili) / 1000 / 60 / 60}:")
                // Minutes
                append("${it.EndTimeMili.minus(it.StartTimeMili) / 1000 / 60}:")
                // Seconds
                append("${it.EndTimeMili.minus(it.StartTimeMili) / 1000}<br><br>")
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }*/

