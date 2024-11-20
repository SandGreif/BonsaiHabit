/*
 * Created by julianmagierski on 16.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import bonsai.habit.common.enums.HealthState
import bonsai.habit.database.BonsaiGardenDatabase
import bonsai.habit.database.dao.BonsaiDao
import bonsai.habit.database.dao.BonsaiStateDao
import bonsai.habit.database.entity.Bonsai
import bonsai.habit.database.entity.BonsaiState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class BonsaiStateDaoTest {
    private lateinit var bonsaiDao: BonsaiDao
    private lateinit var bonsaiStateDao: BonsaiStateDao
    private lateinit var database: BonsaiGardenDatabase

    private val bonsai = Bonsai(1, 10, 1, HealthState.GROWING)
    private val bonsaiState = BonsaiState(2, 5, 1, HealthState.GROWING)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BonsaiGardenDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        bonsaiStateDao = database.bonsaiStateDao()
        bonsaiDao = database.bonsaiDao()
        bonsaiDao.insert(bonsai)
        bonsaiStateDao.insert(bonsaiState)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insert_inserted() {
        val insertedBonsaiState = bonsaiStateDao.getById(2)
        assertEquals(bonsaiState.screenTime, insertedBonsaiState.screenTime)
        assertEquals(HealthState.GROWING, insertedBonsaiState.healthState)
        assertEquals(1, insertedBonsaiState.bonsaiId)
    }

    @Test
    fun test_existsForDay_true() {
        val result = bonsaiStateDao.entityExistsForDay(bonsaiState.createdAt)
        assertTrue(result)
    }

    @Test
    fun test_existsForDay_false() {
        val dateString = "2024-01-01T05:00:00"
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val localDateTime = LocalDateTime.parse(dateString, formatter)
        val result = bonsaiStateDao.entityExistsForDay(localDateTime)
        assertFalse(result)
    }
}
