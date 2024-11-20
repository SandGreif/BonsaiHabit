/*
 * Created by julianmagierski on 16.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bonsai.habit.database.entity.BonsaiState
import java.time.LocalDateTime

@Dao
interface BonsaiStateDao {
    @Insert
    fun insert(bonsaiState: BonsaiState)

    @Query("SELECT * FROM BonsaiState WHERE id = :id")
    fun getById(id: Int): BonsaiState

    // https://stackoverflow.com/questions/68639517/compare-only-date-without-time-using-android-room
    @Query("SELECT EXISTS(SELECT 1 FROM BonsaiState WHERE date(createdAt / 1000,'unixepoch') = date(:dateTime / 1000,'unixepoch'))")
    fun entityExistsForDay(dateTime: LocalDateTime): Boolean
}
