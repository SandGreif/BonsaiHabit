/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(
    entity = Bonsai::class,
    parentColumns = ["id"],
    childColumns = ["bonsaiId"]
)])
data class BonsaiState (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "actualTime")
    val actualTime: Int,
    val bonsaiId: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)