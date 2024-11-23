/*
 * Created by julianmagierski on 21.11.2024
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
import bonsai.habit.usageStatistic.BonsaiStateManager
import bonsai.habit.usageStatistic.UsageStatistic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BonsaiStateManagerTest {
    private lateinit var bonsaiDao: BonsaiDao
    private lateinit var bonsaiStateDao: BonsaiStateDao
    private lateinit var database: BonsaiGardenDatabase

    private val bonsai = Bonsai(1, 10, 1, HealthState.GROWING)
    private val bonsaiStateManager = BonsaiStateManager()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BonsaiGardenDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        bonsaiStateDao = database.bonsaiStateDao()
        bonsaiDao = database.bonsaiDao()
        bonsaiDao.insert(bonsai)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_saveBonsaiState_created() = runTest {
        assertNull(bonsaiStateDao.getById(1))
        bonsaiStateManager.saveBonsaiState(
            context = ApplicationProvider.getApplicationContext(),
            db = database
        )
        val createdBonsaiState = bonsaiStateDao.getById(1)
        assertNotNull(createdBonsaiState)
        assertEquals(HealthState.DYING, createdBonsaiState?.healthState)
    }
}
