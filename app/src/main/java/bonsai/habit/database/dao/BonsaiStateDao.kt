/*
 * Created by julianmagierski on 16.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bonsai.habit.database.entity.BonsaiState

@Dao
interface BonsaiStateDao {
    @Insert
    fun insert(bonsaiState: BonsaiState)

    @Query("SELECT * FROM BonsaiState WHERE id = :id")
    fun getBonsaiStateById(id: Int): BonsaiState
}
