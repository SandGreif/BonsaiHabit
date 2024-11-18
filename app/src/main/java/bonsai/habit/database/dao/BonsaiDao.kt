/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bonsai.habit.database.entity.Bonsai

@Dao
interface BonsaiDao {
    @Insert
    fun insert(bonsai: Bonsai)

    @Query("SELECT * FROM Bonsai WHERE id = :id")
    fun getById(id: Int): Bonsai
}