/*
 * Created by julianmagierski on 18.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.common.converter

import androidx.room.TypeConverter
import bonsai.habit.common.enums.HealthState

class HealthStateTypeConverter {
    @TypeConverter
    fun fromHealthState(healthState: HealthState): Int {
        return healthState.ordinal
    }

    @TypeConverter
    fun toPriority(value: Int): HealthState {
        return HealthState.values()[value]
    }
}