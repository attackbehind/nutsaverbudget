package com.goofy.nutsaver.features

import java.time.YearMonth


fun getCurrentMonthDays(): List<String> {
    val currentMonth = YearMonth.now()
    val monthValue = currentMonth.month
    val yearValue = currentMonth.year
    val daysInMonth = currentMonth.lengthOfMonth()

    return (1..daysInMonth).map { day ->
        "$monthValue, $day $yearValue"
    }
}