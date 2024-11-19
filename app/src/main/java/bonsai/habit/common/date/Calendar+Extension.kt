/*
 * Created by julianmagierski on 19.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.common.date

import java.util.Calendar

fun Calendar.getStartOfDayInMs(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}
