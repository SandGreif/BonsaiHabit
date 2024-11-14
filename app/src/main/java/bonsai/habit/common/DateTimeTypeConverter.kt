/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.common

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class DateTimeTypeConverter {
    @TypeConverter
    fun toDateTime(dateTimeLong: Long?): LocalDateTime? {
        val instant = dateTimeLong?.let { Instant.ofEpochMilli(it) }
        val zoneId = ZoneId.systemDefault()
        return LocalDateTime.ofInstant(instant, zoneId)
    }

    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime?): Long? {
        val zoneId = ZoneId.systemDefault()
        val zonedDateTime = dateTime?.atZone(zoneId)
        return zonedDateTime?.toInstant()?.toEpochMilli()
    }
}