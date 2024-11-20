/*
 * Created by julianmagierski on 19.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.common.time

const val MS_TO_MINUTES = 60000

fun Long.msToMinutes(): Long {
    return this / MS_TO_MINUTES
}