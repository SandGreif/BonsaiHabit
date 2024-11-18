/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import bonsai.habit.common.enums.HealthState
import java.time.LocalDateTime

@Entity(
    foreignKeys = [ForeignKey(
        entity = Bonsai::class,
        parentColumns = ["id"],
        childColumns = ["bonsaiId"],
        onDelete = ForeignKey.CASCADE,
    )]
)
data class BonsaiState(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "screenTime")
    val screenTime: Int,
    @ColumnInfo(name = "bonsaiId")
    val bonsaiId: Int,
    @ColumnInfo(name = "healthState")
    val healthState: HealthState,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)