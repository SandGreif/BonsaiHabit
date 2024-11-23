/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bonsai.habit.common.enums.HealthState
import java.time.LocalDateTime

@Entity
data class Bonsai(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "targetTime")
    val targetTime: Long,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "healthState")
    val healthState: HealthState,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
