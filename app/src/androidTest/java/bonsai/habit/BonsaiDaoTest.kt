/*
 * Created by julianmagierski on 18.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import bonsai.habit.common.enums.HealthState
import bonsai.habit.database.BonsaiGardenDatabase
import bonsai.habit.database.dao.BonsaiDao
import bonsai.habit.database.entity.Bonsai
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BonsaiDaoTest {
    private lateinit var bonsaiDao: BonsaiDao
    private lateinit var database: BonsaiGardenDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BonsaiGardenDatabase::class.java
        ).build()
        bonsaiDao = database.bonsaiDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insert_inserted() {
        val bonsai = Bonsai(1, 10, 1, HealthState.GROWING)
        bonsaiDao.insert(bonsai)

        val insertedBonsai = bonsaiDao.getById(1)
        assertEquals(10, insertedBonsai.targetTime)
        assertEquals(1, insertedBonsai.age)
        assertEquals(HealthState.GROWING, insertedBonsai.healthState)
        assertEquals(1, insertedBonsai.id)
    }
}