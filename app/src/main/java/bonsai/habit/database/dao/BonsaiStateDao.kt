/*
 * Created by julianmagierski on 16.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bonsai.habit.database.entity.BonsaiState
import java.time.LocalDateTime

@Dao
interface BonsaiStateDao {
    @Insert
    fun insert(bonsaiState: BonsaiState)

    @Update
    fun update(bonsaiState: BonsaiState)

    @Query("SELECT * FROM BonsaiState WHERE id = :id")
    fun getById(id: Int): BonsaiState?

    @Query("SELECT * FROM BonsaiState WHERE date(createdAt / 1000,'unixepoch') " +
            "= date(:dateTime / 1000,'unixepoch') LIMIT 1")
    fun entityForDay(dateTime: LocalDateTime): BonsaiState?
}
